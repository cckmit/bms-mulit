package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.DiningAppointmentRecordFoodLibDao;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordFoodLibDTO;
import cn.amigosoft.modules.dining.entity.DiningAppointmentRecordFoodLibEntity;
import cn.amigosoft.modules.dining.service.DiningAppointmentRecordFoodLibService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 下午茶预约记录菜品关联表 服务实现类
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:33:57
 */
@Service
public class DiningAppointmentRecordFoodLibServiceImpl extends CrudServiceImpl<DiningAppointmentRecordFoodLibDao, DiningAppointmentRecordFoodLibEntity, DiningAppointmentRecordFoodLibDTO> implements DiningAppointmentRecordFoodLibService {

    @Override
    public QueryWrapper<DiningAppointmentRecordFoodLibEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String tenantId = (String) params.get("tenantId");
        String diningFoodLibId = (String) params.get("diningFoodLibId");
        String diningAppointmentRecordId = (String) params.get("diningAppointmentRecordId");
        String num = (String) params.get("num");
        String creator = (String) params.get("creator");
        String createDate = (String) params.get("createDate");
        String updater = (String) params.get("updater");
        String updateDate = (String) params.get("updateDate");
        QueryWrapper<DiningAppointmentRecordFoodLibEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(tenantId), "tenant_id", tenantId);
        wrapper.eq(StringUtils.isNotBlank(diningFoodLibId), "dining_food_lib_id", diningFoodLibId);
        wrapper.eq(StringUtils.isNotBlank(diningAppointmentRecordId), "dining_appointment_record_id", diningAppointmentRecordId);
        wrapper.eq(StringUtils.isNotBlank(num), "num", num);
        wrapper.eq(StringUtils.isNotBlank(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotBlank(createDate), "create_date", createDate);
        wrapper.eq(StringUtils.isNotBlank(updater), "updater", updater);
        wrapper.eq(StringUtils.isNotBlank(updateDate), "update_date", updateDate);
        return wrapper;
    }

}

