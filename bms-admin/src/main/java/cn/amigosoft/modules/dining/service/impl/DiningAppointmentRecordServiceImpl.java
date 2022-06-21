package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dao.DiningAppointmentRecordDao;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordInfoDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordPageDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRemindDTO;
import cn.amigosoft.modules.dining.entity.DiningAppointmentRecordEntity;
import cn.amigosoft.modules.dining.service.DiningAppointmentRecordService;
import cn.amigosoft.modules.person.service.PersonService;
import cn.amigosoft.modules.security.user.SecurityUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 下午茶预约记录 服务实现类
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:30:12
 */
@Service
public class DiningAppointmentRecordServiceImpl extends CrudServiceImpl<DiningAppointmentRecordDao, DiningAppointmentRecordEntity, DiningAppointmentRecordDTO> implements DiningAppointmentRecordService {

    @Autowired
    private PersonService personService;

    @Override
    public QueryWrapper<DiningAppointmentRecordEntity> getWrapper(Map<String, Object> params) {
        String diningRoomId = (String) params.get("diningRoomId");
        String appointPickUpTime = (String) params.get("appointPickUpTime");
        String status = (String) params.get("status");
        QueryWrapper<DiningAppointmentRecordEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(diningRoomId), "dining_room_id", diningRoomId);
        wrapper.eq(StringUtils.isNotBlank(appointPickUpTime), "appoint_pick_up_time", appointPickUpTime);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);
        return wrapper;
    }

    /**
     * 菜品预约记录分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageData<DiningAppointmentRecordPageDTO> getPage(Map<String, Object> params) {
        //分页
        IPage<DiningAppointmentRecordEntity> page = getPage(params, "create_date", false);
        //查询
        List<DiningAppointmentRecordPageDTO> data = baseDao.getList(page, params);
        return getPageData(data, page.getTotal(), DiningAppointmentRecordPageDTO.class);
    }

    @Override
    public Result appointmentFinsh(Long id) {
        Date date = new Date();
        Result result = new Result();

        //预约记录
        DiningAppointmentRecordEntity diningAppointmentRecordEntity = this.selectById(id);
        if (diningAppointmentRecordEntity == null) {
            return result.error(ErrorCode.NOT_THIS_RECORD_50102);
        }
        if (!diningAppointmentRecordEntity.getStatus().equals(Constant.DiningAppointmentRecordStatus.WAIT)) {
            return result.error(ErrorCode.STATUS_ERR_50103);
        }
       /* //当前用户是否是餐厅管理员
        Integer num = personService.countBySysUserIdAndDiningRoomId(SecurityUser.getUserId(), diningAppointmentRecordEntity.getDiningRoomId());
        if (num < 1) {
            return result.error(ErrorCode.MUST_BE_ADMIN_CAN_FINISH_50108);
        }*/

        diningAppointmentRecordEntity.setStatus(Constant.DiningAppointmentRecordStatus.FINISH);
        diningAppointmentRecordEntity.setUpdateDate(date);
        diningAppointmentRecordEntity.setUpdater(SecurityUser.getUserId());
        diningAppointmentRecordEntity.setRealPickUpTime(date);
        this.updateById(diningAppointmentRecordEntity);

        return result;
    }

    /**
     * 菜品预约记录详细
     *
     * @param id
     * @return
     */
    @Override
    public DiningAppointmentRecordInfoDTO getInfo(Long id) {
        return baseDao.getInfo(id);
    }

    @Override
    public List<DiningAppointmentRemindDTO> getByStatusAndNoTenantIdAndCreateDate(Integer status, String pickUpTime) {
        return baseDao.getByStatusAndNoTenantIdAndCreateDate(status, pickUpTime);
    }
}

