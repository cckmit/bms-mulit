package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderDetailEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetail2Excel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表
 */
@Mapper
public interface BmsOrderDetailDao extends BaseDao<BmsOrderDetailEntity> {

    List<BmsOrderDetailDTO> selectPageList(@Param("page") IPage<BmsOrderDetailEntity> page, @Param("params") Map<String, Object> params);

    List<BmsOrderDetailDTO> selectOrderDetailByOrderId(Long id);

    List<BmsOrderDetailDTO> selectOrderDetailByIds(@Param("ids") List<BmsOrderDetailDTO> list);

    List<BmsOrderDetailDTO> selectExportList(Map<String, Object> params);

    int selectUserDetailCount(@Param("creator") Long creator, @Param("eatDate") Object eatDate, @Param("mealTypeId") Object mealTypeId);

    List<BmsOrderDetail2Excel> selectExportDataByOrderId(Long orderId);

    int updateStatus();

    List<BmsOrderDetailDTO> selectCountPageList(@Param("page") IPage<BmsOrderDetailEntity> page, @Param("params") Map<String, Object> params);

    List<BmsOrderDetailDTO> selectCountExportList(Map<String, Object> params);

    int deleteByOrderIds(@Param("orderIds") List<Long> orderIds);

    List<BmsOrderDetailDTO> selectMonthCancelList(Long creator);

}