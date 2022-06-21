package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 */
@Mapper
public interface BmsOrderDao extends BaseDao<BmsOrderEntity> {

    List<BmsOrderDTO> selectPageList(@Param("page") IPage<BmsOrderEntity> page, @Param("params") Map<String, Object> params);

    BmsOrderDTO selectOrderById(Long id);

    List<BmsOrderDTO> selectExportList(Map<String, Object> params);

}