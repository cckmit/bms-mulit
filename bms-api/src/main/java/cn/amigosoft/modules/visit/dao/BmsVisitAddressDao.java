package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitAddressEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 访问地点表 
 */
@Mapper
public interface BmsVisitAddressDao extends BaseDao<BmsVisitAddressEntity> {

    List<BmsVisitAddressDTO> getChooseList();
}