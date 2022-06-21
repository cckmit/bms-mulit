package cn.amigosoft.modules.bms.csv;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BmsCsvImporterImpl implements BmsImporter {

    /**
     * 线程个数据
     */
    private int threadCount = 10;
    /**
     * 批量导入个数
     */
    private int batchCount = 500;

    /**
     * 总条数
     */
    private int allCount = 0;

    /**
     * 导入成功数量
     */
    private int successCount = 0;

    /**
     * 导入失败数量
     */
    private int errorCount = 0;

    /**
     * 行数据处理器执行器
     */
    private BmsCsvImporterRunner importerRunner;

    private ThreadPoolExecutor threadPoolExecutor;

    private Map<String, Object> param;

    private MultipartFile multipartFile;

    public BmsCsvImporterImpl(MultipartFile multipartFile, BmsCsvImporterRunner importerRunner, Map<String, Object> param) {
        this.multipartFile = multipartFile;
        this.importerRunner = importerRunner;
        this.param = param;
    }

    /**
     * 导入每行结果集
     */
    private Map<Integer, BmsRowImportResult> resultsMap = new HashMap<>();

    /**
     * 处理条数
     */
    private int processResultSortId = 1;

    private boolean isProcessResult = true;

    private void processResult() {
        try {
            while (isProcessResult) {
                BmsRowImportResult importResult = resultsMap.get(processResultSortId);
                if (importResult != null) {
                    if (importResult.isSuccess()) {
                        successCount++;
                    } else {
                        errorCount++;
                    }
                    resultsMap.remove(processResultSortId);
                    processResultSortId++;
                }
            }
            allCount = successCount + errorCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = new File(file.getOriginalFilename());
        InputStream ins = file.getInputStream();
        inputStreamToFile(ins, toFile);
        ins.close();
        return toFile;
    }

    /**
     * 获取流文件
     */
    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BmsFileImportResult start() throws Exception {
        BmsFileImportResult importResult;
        LineIterator iterator = null;
        File file = null;
        try {
            BmsCsvImporterImpl csvImporter = this;
            threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
            // 处理结果线程
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    processResult();
                }
            });
            // 分批导入数据
            List objs = new ArrayList<>();
            // 分批结论数据
            List<BmsRowImportResult> results = new ArrayList<>();
            file = multipartFileToFile(multipartFile);
            //  加载读取CSV
            iterator = FileUtils.lineIterator(file, "GBK");
            // 忽略第一行的标题信息
            iterator.nextLine();

            String line = null;
            // 循环导入中每行记录
            int allCount = 0;
            int curBatchCount = batchCount;
            while (iterator.hasNext()) {
                line = iterator.nextLine();
                // CSV格式文件为逗号分隔符文件
                String[] item = line.split(",", -1);
                for (int i = 0; i < item.length; i++) {
                    item[i] = item[i].trim();
                }
                allCount++;
                // 对CSV 行 转成 对象
                Object o = importerRunner.rowToObj(line, item, csvImporter);
                if (o != null) {
                    objs.add(o);
                }
                // 创建导入结果对象
                BmsRowImportResult result = new BmsRowImportResult();
                result.setSortId(allCount);
                results.add(result);

                if (allCount >= curBatchCount) {
                    curBatchCount = curBatchCount + batchCount;
                    processImport(objs, results, csvImporter);
                    objs = new ArrayList<>();
                    results = new ArrayList<>();
                }
                // 线程池有空闲再继续执行
                while (threadPoolExecutor.getActiveCount() == threadCount) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 仍存在未导入的数据
            if (!objs.isEmpty()) {
                processImport(objs, results, csvImporter);
            }
            // 等待结果线程处理完成
            while (processResultSortId <= allCount) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            importResult = new BmsFileImportResult();
            isProcessResult = false;
            threadPoolExecutor.shutdown();
            importResult.setAllCount(this.allCount);
            importResult.setSuccessCount(successCount);
            importResult.setErrorCount(errorCount);
        } catch (Exception e) {
            e.printStackTrace();
            threadPoolExecutor.shutdown();
            throw e;
        } finally {
            if (iterator != null) {
                LineIterator.closeQuietly(iterator);
            }
            if (file != null) {
                file.delete();
            }
        }
        return importResult;
    }

    private void processImport(List objs, List<BmsRowImportResult> results, BmsImporter csvImporter) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    importerRunner.batchInsert(objs, results, csvImporter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                addCSVImportResult(results);
            }
        });
    }

    private void addCSVImportResult(List<BmsRowImportResult> results) {
        for (BmsRowImportResult r : results) {
            resultsMap.put(r.getSortId(), r);
        }
    }

    @Override
    public Map<String, Object> getParam() {
        return param;
    }
}
