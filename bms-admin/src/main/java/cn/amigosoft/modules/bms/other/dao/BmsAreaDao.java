package cn.amigosoft.modules.bms.other.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.other.dto.BmsAreaDTO;
import cn.amigosoft.modules.bms.other.entity.BmsAreaEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 区域表 
 */
@Mapper
public interface BmsAreaDao extends BaseDao<BmsAreaEntity> {

    List<BmsAreaDTO> selectAreaList();

}