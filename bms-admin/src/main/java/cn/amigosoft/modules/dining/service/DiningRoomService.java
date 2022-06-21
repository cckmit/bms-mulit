package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.DiningRoomDTO;
import cn.amigosoft.modules.dining.dto.DiningRoomPageRespDTO;
import cn.amigosoft.modules.dining.dto.SaveOrUpdateDiningRoomReqDTO;
import cn.amigosoft.modules.dining.entity.DiningRoomEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智慧餐厅 服务接口类
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
public interface DiningRoomService extends CrudService<DiningRoomEntity, DiningRoomDTO> {

    Result saveOrUpdate(SaveOrUpdateDiningRoomReqDTO reqDTO, boolean isSaveFlag);

    Result<PageData<DiningRoomPageRespDTO>> getPage(Map<String, Object> params);

    Result del(Long[] ids);

    Long getIdByName(String name);

    List<DiningRoomDTO> selectList();

    DiningRoomDTO info(Long id);
}

