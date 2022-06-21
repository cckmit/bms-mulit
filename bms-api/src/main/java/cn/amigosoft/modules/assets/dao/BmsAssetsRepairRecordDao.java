package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资产维修记录表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Mapper
public interface BmsAssetsRepairRecordDao extends BaseDao<BmsAssetsRepairRecordEntity> {

    List<BmsAssetsRepairRecordDTO> getRepairRecordList(@Param("assetsRepairId") Long assetsRepairId);
}