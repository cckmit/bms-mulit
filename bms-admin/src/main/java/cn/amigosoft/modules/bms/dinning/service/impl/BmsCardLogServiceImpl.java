package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.utils.ImportUtils;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.csv.*;
import cn.amigosoft.modules.bms.dinning.dao.BmsCardLogDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsOrderDetailDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsCardLogDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsCardLogEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsVerificationStatisticsEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsCardLogService;
import cn.amigosoft.modules.bms.dinning.service.BmsVerificationStatisticsService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 一卡通记录表
 */
@Slf4j
@Service
public class BmsCardLogServiceImpl extends CrudServiceImpl<BmsCardLogDao, BmsCardLogEntity, BmsCardLogDTO> implements BmsCardLogService, BmsCsvImporterRunner {

    @Autowired
    private BmsVerificationStatisticsService verificationStatisticsService;

    @Autowired
    private BmsOrderDetailDao orderDetailDao;

    @Override
    public QueryWrapper<BmsCardLogEntity> getWrapper(Map<String, Object> params) {

        QueryWrapper<BmsCardLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);

        return wrapper;
    }

    @Override
    public void importCsv(MultipartFile file, HttpServletResponse response) throws Exception {
        long a = System.currentTimeMillis();
        String importCode = String.valueOf(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>(1);
        map.put("importCode", importCode);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        Date createDate = new Date();
        map.put("creator", creator);
        map.put("createDate", createDate);
        BmsFileImportResult importResult = new BmsCsvImporterImpl(file, this, map).start();
        int allCount = importResult.getAllCount();
        if (allCount == 0) {
            ImportUtils.noDataImport("文件中未包含数据", response);
            return;
        }
        int successCount = importResult.getSuccessCount();
        int errorCount = importResult.getErrorCount();
        // 删除一卡通记录表重复数据
        long b = System.currentTimeMillis();
        baseDao.deleteDuplicateData();
        long c = System.currentTimeMillis();
        // 更新餐别ID
        baseDao.updateMealTypeId();
        long d = System.currentTimeMillis();

        // 存入核销统计数据
        // 查询需要存入核销表的数据
        List<BmsVerificationStatisticsEntity> entityList = verificationStatisticsService.selectDataFromCardLog(importCode);
        long e = System.currentTimeMillis();

        if (entityList.size() > 0) {
            verificationStatisticsService.batchInsertVerificationStatistics(entityList, creator, createDate);
            long f = System.currentTimeMillis();

            // 删除核销表重复数据
            verificationStatisticsService.deleteDuplicateData();
            long g = System.currentTimeMillis();

            // 更新核销状态
            int updateCount = verificationStatisticsService.updateStatus();
            long h = System.currentTimeMillis();
            // 更新核销表关联的订单详情ID
            verificationStatisticsService.updateOrderDetailId();
            long i = System.currentTimeMillis();

            // 更新订单详情状态
            if (updateCount > 0) {
                orderDetailDao.updateStatus();
            }
            long j = System.currentTimeMillis();

            log.info("存入一卡通记录表：" + (b - a));
            log.info("删除一卡通记录表重复数据：" + (c - b));
            log.info("更新一卡通记录表餐别ID：" + (d - c));
            log.info("从一卡通记录表查询核销数据：" + (e - d));
            log.info("插入核销数据：" + (f - e));
            log.info("删除核销表重复数据：" + (g - f));
            log.info("更新核销状态：" + (h - g));
            log.info("更新核销表关联的订单详情ID：" + (i - h));
            log.info("更新订单详情状态：" + (j - i));
        }
        long z = System.currentTimeMillis();
        log.info("总耗时：" + (z - a));
        if (allCount == successCount) {
            ImportUtils.successImport(allCount, response);
        } else {
            ImportUtils.errorImport(successCount, errorCount, response);
        }
    }

    @Override
    public Object rowToObj(String line, String[] row, BmsImporter importer) {
        BmsCardLogEntity entity;
        try {
            entity = new BmsCardLogEntity();
            entity.setSerialNum(Long.parseLong(row[0]));
            entity.setAccount(row[1]);
            entity.setRealName(row[2]);
            entity.setUserCode(row[3]);
            entity.setCardType(row[4]);
            entity.setBusinessArea(row[5]);
            entity.setBusinessSite(row[6]);
            entity.setBusinessAddress(row[7]);
            entity.setTerminalCode(row[8]);
            entity.setBusinessType(row[9]);
            entity.setBusinessPurse(row[10]);
            entity.setBusinessMoney(row[11]);
            entity.setCardBalance(row[12]);
            entity.setStockBalance(row[13]);
            entity.setPurseSerial(row[14]);
            entity.setBusinessDate(row[15]);
            entity.setTransferredDate(row[16]);
            entity.setBusinessCode(row[17]);
            entity.setBusinessName(row[18]);
            entity.setDiscountMoney(row[19]);
            entity.setDeposit(row[20]);
            entity.setCardFees(row[21]);
            entity.setCharges(row[22]);
            entity.setOperatorCode(row[23]);
            entity.setAccountDept(row[24]);
            entity.setTransactionMode(row[25]);
            entity.setPaymentChannel(row[26]);
            Map<String, Object> param = importer.getParam();
            String importCode = param.get("importCode").toString();
            entity.setImportCode(importCode);
            entity.setCreator(Long.parseLong(param.get("creator").toString()));
            entity.setCreateDate((Date) param.get("createDate"));
            if (row.length > 27) {
                entity.setRemark(row[27]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    @Override
    public void batchInsert(List dataList, List<BmsRowImportResult> importResultList, BmsImporter csvImporter) {
        baseDao.batchInsertCardLog(dataList);
    }
}
