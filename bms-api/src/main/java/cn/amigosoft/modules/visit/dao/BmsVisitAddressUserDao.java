package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressUserDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitAddressUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 访问地点用户关联表
 */
@Mapper
public interface BmsVisitAddressUserDao extends BaseDao<BmsVisitAddressUserEntity> {

    BmsVisitAddressUserDTO getVerifyUserId(@Param("addressId") Long addressId);
}
