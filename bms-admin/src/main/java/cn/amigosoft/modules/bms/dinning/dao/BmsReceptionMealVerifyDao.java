package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVerifyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接待餐审批表
 */
@Mapper
public interface BmsReceptionMealVerifyDao extends BaseDao<BmsReceptionMealVerifyEntity> {

    List<BmsReceptionMealVerifyDTO> selectVerifyListByReceptionMealId(Long receptionMealId);
}