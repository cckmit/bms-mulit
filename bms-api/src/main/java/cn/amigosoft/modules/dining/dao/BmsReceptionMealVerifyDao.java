package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVerifyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接待餐审批表
 */
@Mapper
public interface BmsReceptionMealVerifyDao extends BaseDao<BmsReceptionMealVerifyEntity> {

    List<BmsReceptionMealVerifyDTO> selectVerifyList(Long receptionMealId);

}