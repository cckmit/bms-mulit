package cn.amigosoft.modules.bms.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表
 */
@Mapper
public interface BmsVisitApplyDao extends BaseDao<BmsVisitApplyEntity> {

    List<BmsVisitApplyDTO> selectPageList(@Param("page") IPage<BmsVisitApplyEntity> page, @Param("params") Map<String, Object> params);

    String selectAddressNameByApplyId(Long applyId);

    List<BmsVisitApplyDTO> selectExportList(Map<String, Object> params);

    BmsVisitApplyDTO selectVisitApplyById(Long id);
}