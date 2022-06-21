package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsVerificationStatisticsDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsVerificationStatisticsEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核销统计表
 */
@Mapper
public interface BmsVerificationStatisticsDao extends BaseDao<BmsVerificationStatisticsEntity> {

    List<BmsVerificationStatisticsEntity> selectDataFromCardLog(String importCode);

    int deleteDuplicateData();

    List<BmsVerificationStatisticsDTO> selectPageList(@Param("page") IPage<BmsVerificationStatisticsEntity> page, @Param("params") Map<String, Object> params);

    List<BmsVerificationStatisticsDTO> selectExportList(Map<String, Object> params);

    int updateStatus();

    int batchInsertVerificationStatistics(@Param("list") List<BmsVerificationStatisticsEntity> list, @Param("creator") Long creator, @Param("createDate") Date createDate);

    int updateOrderDetailId();

}
