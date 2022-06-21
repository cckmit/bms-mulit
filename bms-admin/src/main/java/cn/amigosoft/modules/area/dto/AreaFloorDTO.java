package cn.amigosoft.modules.area.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


/**
 * 楼层信息表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "楼层信息表 ")
public class AreaFloorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@Null(message="{area.floor.id.null}", groups = AddGroup.class)
	@NotNull(message="{area.floor.id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "区域ID")
	private Long areaId;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "楼栋ID")
	private Long areaBuildingId;

	@ApiModelProperty(value = "楼层信息（哪层）")
	@NotNull(message = "{area.floorNum.require}",groups = {AddGroup.class, UpdateGroup.class})
	private Integer floorNum;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "是否使用（0：无 1：有）")
	private Integer useStatus;

	@ApiModelProperty(value = "是否删除（0未删除，1已删除）")
	private Integer del;


}