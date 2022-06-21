package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@ApiModel(value = "DiningAppointmentRecordFoodLib对象", description = "下午茶预约记录菜品关联表")
@Data
public class DiningAppointmentRecordFoodLibDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("预约记录id")
    private Long id;

    @ApiModelProperty("租户Id")
    private Long tenantId;

    @ApiModelProperty("菜品库Id iotsaas_dining_foods_lib.id")
    private Long diningFoodLibId;

    @ApiModelProperty("预约记录Id iotsaas_dining_appointment_record.id")
    private Long diningAppointmentRecordId;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("创建者id")
    private Long creator;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改者 id")
    private Long updater;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
