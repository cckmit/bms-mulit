package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 下午茶预约记录 实体类
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:30:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_appointment_record")
public class DiningAppointmentRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 预约人Id iotsaas_person_staff.id
     */
    private Long personStaffId;

    /**
     * 餐厅Id iotsaas_dining_room.id
     */
    private Long diningRoomId;

    /**
     * 预约取餐时间
     */
    private Date appointPickUpTime;

    /**
     * 实际取餐时间
     */
    private Date realPickUpTime;

    /**
     * 状态  0:待取餐, 1:已完成, 3:已取消
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改者 id
     */
    private Long updater;

    /**
     * 修改日期
     */
    private Date updateDate;

}
