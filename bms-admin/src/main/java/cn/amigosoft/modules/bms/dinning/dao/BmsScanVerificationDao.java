package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsScanVerificationEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 扫码核销表
 */
@Mapper
public interface BmsScanVerificationDao extends BaseDao<BmsScanVerificationEntity> {

    List<BmsScanVerificationDTO> selectPageList(@Param("page") IPage<BmsScanVerificationEntity> page, @Param("params") Map<String, Object> params);

    List<BmsScanVerificationDTO> selectExportList(Map<String, Object> params);

}
