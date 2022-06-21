package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.DiningRoomPageRespDTO;
import cn.amigosoft.modules.dining.entity.DiningRoomEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智慧餐厅 Dao 接口
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@Mapper
public interface DiningRoomDao extends BaseDao<DiningRoomEntity> {

    List<DiningRoomPageRespDTO> selectPageList(IPage<DiningRoomEntity> iPage, @Param("params") Map<String, Object> params);

    void updateRoomById(@Param("diningRoomEntity") DiningRoomEntity diningRoomEntity);
}
