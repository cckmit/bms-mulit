package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDTO;
import cn.amigosoft.modules.dining.entity.BmsOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Mapper
public interface BmsOrderDao extends BaseDao<BmsOrderEntity> {

    //报餐记录列表
    List<BmsOrderDTO> queryPage(Map<String, Object> params);

    //获取报餐订单详情的基本信息
    BmsOrderDTO getBaseInfo(@Param("id") Long id);

    //根据订单ID删除对应的餐次
    void updateDelDetails(@Param("orderId") Long orderId);

    //根据餐品ID获取其提前订餐小时数和用餐开始时间
    BmsMealTypeDTO getAdvanceAndBeginTime(@Param("id") Long id);

    //根据餐品ID和报餐人ID获取当天至周日中该餐品的已订餐日期
    List<String> getAlreadyOrderDate(Map<String, Object> params);
}