package cn.amigosoft.modules.bms.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.bms.visit.excel.BmsVisitApplyVisitorExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员报备访客关联表 
 */
@Mapper
public interface BmsVisitApplyVisitorDao extends BaseDao<BmsVisitApplyVisitorEntity> {

    String selectVisitorNameByApplyId(Long applyId);

    List<BmsVisitApplyVisitorExcel> selectVisitorExcelDataByApplyId(Long applyId);

}