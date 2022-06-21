package cn.amigosoft.modules.bms.csv;


import java.util.List;

public interface BmsCsvImporterRunner {

    /**
     * 把CSV的行转成对象
     *
     * @param line     行原始数据
     * @param row      行原始对象
     * @param importer ItvCSVImporter的实现类
     * @return 所需数据对象
     */
    Object rowToObj(String line, String[] row, BmsImporter importer);

    /**
     * 批量或逐条验证并导入
     *
     * @param dataList         数据对象列表
     * @param importResultList 导入结果列表
     * @param csvImporter      导入实现类，主要用于获取自定义业务参数
     */
    void batchInsert(List dataList, List<BmsRowImportResult> importResultList, BmsImporter csvImporter);


}
