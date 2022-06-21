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
 * 楼栋信息表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "楼栋信息表 ")
public class AreaBuildingDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@Null(message="{area.building.id.null}", groups = AddGroup.class)
	@NotNull(message="{area.building.id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "区域ID")
	private Long areaId;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "楼栋编号")
	@NotBlank(message = "{area.buildingName.require}",groups = {AddGroup.class, UpdateGroup.class})
	private String buildingCode;

	@ApiModelProperty(value = "起始层")
	@NotNull(message = "{area.firstFloor.require}",groups = {AddGroup.class, UpdateGroup.class})
	private Integer firstFloor;

	@ApiModelProperty(value = "层数")
	@NotNull(message = "{area.floorNum.require}",groups = {AddGroup.class, UpdateGroup.class})
	private Integer floorNum;

	@ApiModelProperty(value = "楼层除外")
	private String extraFloor;

	@ApiModelProperty(value = "房间数")
	@NotNull(message = "{area.roomNum.require}",groups = {AddGroup.class, UpdateGroup.class})
	private Integer roomNum;

	@ApiModelProperty(value = "房间除外")
	private String extraRoom;

	@ApiModelProperty(value = "是否删除")
	private Integer del;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;


}