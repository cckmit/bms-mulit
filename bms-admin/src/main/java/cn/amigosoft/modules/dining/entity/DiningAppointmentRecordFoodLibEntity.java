package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 下午茶预约记录菜品关联表 实体类
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:33:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_appointment_record_food_lib")
public class DiningAppointmentRecordFoodLibEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 菜品库Id iotsaas_dining_foods_lib.id
     */
    private Long diningFoodLibId;

    /**
     * 预约记录Id iotsaas_dining_appointment_record.id
     */
    private Long diningAppointmentRecordId;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 修改者 id
     */
    private Long updater;

    /**
     * 修改日期
     */
    private Date updateDate;

}
