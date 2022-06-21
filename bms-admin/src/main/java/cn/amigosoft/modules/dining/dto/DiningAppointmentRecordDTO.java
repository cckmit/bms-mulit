package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@ApiModel(value = "DiningAppointmentRecord对象", description = "下午茶预约记录")
@Data
public class DiningAppointmentRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("预约记录id")
    private Long id;

    @ApiModelProperty("租户Id")
    private Long tenantId;

    @ApiModelProperty("预约人Id iotsaas_person_staff.id")
    private Long personStaffId;

    @ApiModelProperty("餐厅Id iotsaas_dining_room.id")
    private Long diningRoomId;

    @ApiModelProperty("预约取餐时间")
    private Date appointPickUpTime;

    @ApiModelProperty("实际取餐时间")
    private Date realPickUpTime;

    @ApiModelProperty("状态  0:待取餐, 1:已完成, 3:已取消")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建者id")
    private Long creator;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改者 id")
    private Long updater;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
