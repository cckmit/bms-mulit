package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVisitorDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVisitorEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsReceptionMealVisitorExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接待餐访客关联表 
 */
@Mapper
public interface BmsReceptionMealVisitorDao extends BaseDao<BmsReceptionMealVisitorEntity> {

    List<BmsReceptionMealVisitorDTO> selectByReceptionMealId(Long receptionMealId);

    List<BmsReceptionMealVisitorExcel> selectExcelInfoByReceptionMealId(Long receptionMealId);

}