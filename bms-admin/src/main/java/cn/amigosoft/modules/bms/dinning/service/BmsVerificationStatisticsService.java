package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsVerificationStatisticsDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsVerificationStatisticsEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核销统计表
 */
public interface BmsVerificationStatisticsService extends CrudService<BmsVerificationStatisticsEntity, BmsVerificationStatisticsDTO> {

    List<BmsVerificationStatisticsEntity> selectDataFromCardLog(String importCode);

    int deleteDuplicateData();

    Result<PageData<BmsVerificationStatisticsDTO>> getPage(Map<String, Object> params);

    List<BmsVerificationStatisticsDTO> selectExportList(Map<String, Object> params);

    int updateStatus();

    int batchInsertVerificationStatistics(List<BmsVerificationStatisticsEntity> list, Long creator, Date createDate);

    int updateOrderDetailId();

}
