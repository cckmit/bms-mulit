package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordInfoDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordPageDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRemindDTO;
import cn.amigosoft.modules.dining.entity.DiningAppointmentRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 下午茶预约记录 服务接口类
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:30:12
 */
public interface DiningAppointmentRecordService extends CrudService<DiningAppointmentRecordEntity, DiningAppointmentRecordDTO> {
    /**
     * 菜品预约记录分页查询
     *
     * @param params
     * @return
     */
    PageData<DiningAppointmentRecordPageDTO> getPage(Map<String, Object> params);

    /**
     * 预约完成
     *
     * @param id
     * @return
     */
    Result appointmentFinsh(Long id);

    /**
     * 菜品预约记录详细
     *
     * @param id
     * @return
     */
    DiningAppointmentRecordInfoDTO getInfo(Long id);

    /**
     * 根据状态免租户查询
     *
     * @param status
     * @return
     */
    List<DiningAppointmentRemindDTO> getByStatusAndNoTenantIdAndCreateDate(Integer status, String pickUpTime);
}

