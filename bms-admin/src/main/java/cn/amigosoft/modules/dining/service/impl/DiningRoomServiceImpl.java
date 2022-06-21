package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.area.entity.AreaEntity;
import cn.amigosoft.modules.area.service.AreaService;
import cn.amigosoft.modules.dining.dao.DiningRoomDao;
import cn.amigosoft.modules.dining.dto.DiningRoomDTO;
import cn.amigosoft.modules.dining.dto.DiningRoomPageRespDTO;
import cn.amigosoft.modules.dining.dto.SaveOrUpdateDiningRoomReqDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodLibEntity;
import cn.amigosoft.modules.dining.entity.DiningRoomEntity;
import cn.amigosoft.modules.dining.service.DiningFoodLibService;
import cn.amigosoft.modules.dining.service.DiningRoomService;
import cn.amigosoft.modules.person.service.PersonService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 智慧餐厅 服务实现类
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@Service
public class DiningRoomServiceImpl extends CrudServiceImpl<DiningRoomDao, DiningRoomEntity, DiningRoomDTO> implements DiningRoomService {

    @Autowired
    private AreaService areaService;

    @Autowired
    private DiningFoodLibService diningFoodLibService;

    @Autowired
    private PersonService personService;

    @Override
    public QueryWrapper<DiningRoomEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String tenantId = (String) params.get("tenantId");
        String name = (String) params.get("name");
        String seats = (String) params.get("seats");
        String maxCapacity = (String) params.get("maxCapacity");
        String areaId = (String) params.get("areaId");
        String areaBuildingId = (String) params.get("areaBuildingId");
        String areaFloorId = (String) params.get("areaFloorId");
        String areaBuildingRoomId = (String) params.get("areaBuildingRoomId");
        String updater = (String) params.get("updater");
        String updateDate = (String) params.get("updateDate");
        String creator = (String) params.get("creator");
        String createDate = (String) params.get("createDate");
        QueryWrapper<DiningRoomEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(tenantId), "tenant_id", tenantId);
        wrapper.eq(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(StringUtils.isNotBlank(seats), "seats", seats);
        wrapper.eq(StringUtils.isNotBlank(maxCapacity), "max_capacity", maxCapacity);
        wrapper.eq(StringUtils.isNotBlank(areaId), "area_id", areaId);
        wrapper.eq(StringUtils.isNotBlank(areaBuildingId), "area_building_id", areaBuildingId);
        wrapper.eq(StringUtils.isNotBlank(areaFloorId), "area_floor_id", areaFloorId);
        wrapper.eq(StringUtils.isNotBlank(areaBuildingRoomId), "area_building_room_id", areaBuildingRoomId);
        wrapper.eq(StringUtils.isNotBlank(updater), "updater", updater);
        wrapper.eq(StringUtils.isNotBlank(updateDate), "update_date", updateDate);
        wrapper.eq(StringUtils.isNotBlank(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotBlank(createDate), "create_date", createDate);
        return wrapper;
    }

    @Override
    public Result saveOrUpdate(SaveOrUpdateDiningRoomReqDTO reqDTO, boolean isSaveFlag) {
        Result result = new Result();
        // 判断餐厅名称是否重复
        QueryWrapper<DiningRoomEntity> wrapper = new QueryWrapper<DiningRoomEntity>();
        String name = reqDTO.getName();
        wrapper.eq("name", name);
        if (!isSaveFlag) {
            // 修改时查询条件过滤自己
            wrapper.ne("id", reqDTO.getId());
        }
        Integer count = selectCount(wrapper);
        if (count > 0) {
            return result.error("已存在相同名称餐厅");
        }
        DiningRoomEntity diningRoomEntity = ConvertUtils.sourceToTarget(reqDTO, DiningRoomEntity.class);
//        AreaEntity areaEntity = areaService.selectById(diningRoomEntity.getAreaId());
//        if (areaEntity == null) {
//            return result.error(ErrorCode.AREA_NOT_EXISIT_ERROR_30005);
//        }
        if (isSaveFlag) {
            insert(diningRoomEntity);
        } else {
            baseDao.updateRoomById(diningRoomEntity);
        }
        return result;
    }

    @Override
    public Result<PageData<DiningRoomPageRespDTO>> getPage(Map<String, Object> params) {
        IPage<DiningRoomEntity> page = getPage(params, "dr.sort,dr.create_date", false);
        List<DiningRoomPageRespDTO> resultList = baseDao.selectPageList(page, params);
        for (DiningRoomPageRespDTO dto : resultList) {
            if (!StringUtil.isEmpty(dto.getAreaFloorId())) {
                dto.setFloorName(dto.getFloorName() + "层");
            }
        }
        return new Result<PageData<DiningRoomPageRespDTO>>().ok(getPageData(resultList, page.getTotal(), DiningRoomPageRespDTO.class));
    }

    @Override
    public Result del(Long[] ids) {
        Result result = new Result();
        // 循环遍历餐厅,查看餐厅下是否有菜品
        for (Long id : ids) {
            Integer count = diningFoodLibService.selectCount(new QueryWrapper<DiningFoodLibEntity>().eq("dining_room_id", id).eq("status", 2));
            if (count > 0) {
                DiningRoomEntity diningRoomEntity = selectById(id);
                return result.error(diningRoomEntity.getName() + "该餐厅下还有关联菜品,请先删除对应的菜品！");
            }
        }
        delete(ids);
        return result;
    }

    @Override
    public Long getIdByName(String name) {
        DiningRoomEntity diningRoomEntity = selectOne(new LambdaQueryWrapper<DiningRoomEntity>().eq(DiningRoomEntity::getName, name));
        return Objects.isNull(diningRoomEntity) ? null : diningRoomEntity.getId();
    }

    @Override
    public List<DiningRoomDTO> selectList() {
        return ConvertUtils.sourceToTarget(baseDao.selectList(null), DiningRoomDTO.class);
    }

    @Override
    public DiningRoomDTO info(Long id) {
        DiningRoomDTO diningRoomDTO = get(id);
        Long personStaffId = diningRoomDTO.getPersonStaffId();
//        PersonStaffEntity personStaffEntity = personStaffService.selectById(personStaffId);
//        if (Objects.nonNull(personStaffEntity)) {
//            PersonEntity personEntity = personService.selectById(personStaffEntity.getPersonId());
//            if (Objects.nonNull(personEntity)) {
//                diningRoomDTO.setManager(personEntity.getName());
//            }
//
//        }
        return diningRoomDTO;
    }
}

