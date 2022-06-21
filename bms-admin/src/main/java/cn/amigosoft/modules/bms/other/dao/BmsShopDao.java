package cn.amigosoft.modules.bms.other.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.other.dto.BmsShopDTO;
import cn.amigosoft.modules.bms.other.entity.BmsShopEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 店铺表
 */
@Mapper
public interface BmsShopDao extends BaseDao<BmsShopEntity> {

    List<BmsShopDTO> selectPageList(@Param("page") IPage<BmsShopEntity> page, @Param("params") Map<String, Object> params);


    List<BmsShopDTO> selectExportList(Map<String, Object> params);

    BmsShopDTO selectShopById(Long id);

}