package cn.amigosoft.modules.bms.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressUserDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 访问地点用户关联表
 */
@Mapper
public interface BmsVisitAddressUserDao extends BaseDao<BmsVisitAddressUserEntity> {

    List<BmsVisitAddressUserDTO> selectByAddressId(Long addressId);

}
