package cn.amigosoft.modules.bms.other.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.other.dto.BmsGoodsDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 货品表
 */
@Mapper
public interface BmsGoodsDao extends BaseDao<BmsGoodsEntity> {

    List<BmsGoodsDTO> selectPageList(@Param("page") IPage<BmsGoodsEntity> page, @Param("params") Map<String, Object> params);

    List<BmsGoodsDTO> selectExportList(Map<String, Object> params);

    BmsGoodsDTO selectGoodsById(Long id);

}