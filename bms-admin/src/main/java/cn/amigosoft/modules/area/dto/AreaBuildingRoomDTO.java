package cn.amigosoft.modules.area.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


/**
 * 房屋信息表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "房屋信息表 ")
public class AreaBuildingRoomDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@Null(message="{area.room.id.null}", groups = AddGroup.class)
	@NotNull(message="{area.room.id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "区域ID")
	private Long areaId;

	@ApiModelProperty(value = "楼栋ID")
	private Long areaBuildingId;

	@ApiModelProperty(value = "楼层id")
	private Long areaFloorId;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "房屋名称")
	@NotBlank(message = "{area.roomName.require}",groups = {AddGroup.class, UpdateGroup.class})
	private String roomName;

	@ApiModelProperty(value = "房屋编号")
	private String roomCode;

	@ApiModelProperty(value = "房屋面积")
	private String roomArea;

	@ApiModelProperty(value = "房屋坐标1(0,0)")
	private String parkingCoordinate1;

	@ApiModelProperty(value = "房屋坐标2(1,1)")
	private String parkingCoordinate2;

	@ApiModelProperty(value = "房屋类型(1:商用,2:独栋,3:高层99:其他)")
	private Integer roomType;

	@ApiModelProperty(value = "使用状态(1:自住,2:放租,3:商用,4:闲置99:其他,)")
	private Integer useStatus;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "是否删除")
	private Integer del;


}