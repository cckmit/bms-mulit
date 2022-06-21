package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 下午茶预约记录分页
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:30:12
 */
@ApiModel(value = "下午茶预约记录分页", description = "下午茶预约记录分页")
@Data
public class DiningAppointmentRecordPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("预约记录id")
    private Long id;

    @ApiModelProperty("组织机构")
    private String deptName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("餐厅名称")
    private String diningName;

    @ApiModelProperty("菜品名称")
    private String foodName;

    @ApiModelProperty("下单时间")
    private Date createDate;

    @ApiModelProperty("实际取餐时间")
    private Date realPickUpTime;

    @ApiModelProperty("状态  0:待取餐, 1:已完成, 2:已取消")
    private Integer status;

}
