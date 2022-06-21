package cn.amigosoft.modules.bms.repair.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资产维修记录表
 */
@Mapper
public interface BmsAssetsRepairRecordDao extends BaseDao<BmsAssetsRepairRecordEntity> {

    List<BmsAssetsRepairRecordDTO> selectAssetsRepairRecordList(Long repairId);

}