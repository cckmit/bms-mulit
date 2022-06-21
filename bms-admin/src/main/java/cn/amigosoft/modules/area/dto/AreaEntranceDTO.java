package cn.amigosoft.modules.area.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 出入口信息表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "出入口信息表 ")
public class AreaEntranceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@NotNull(message = "出入口ID不能为空",groups = {UpdateGroup.class})
	private Long id;

	@ApiModelProperty(value = "区域ID")
	private Long areaId;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "编号")
	private String entranceCode;

	@ApiModelProperty(value = "出入口类型(1:正门,2:侧门,3:后门,4:其他)")
	private Integer entranceType;

	@ApiModelProperty(value = "出入口尺寸")
	private String entranceSize;

	@ApiModelProperty(value = "出入口方位")
	private String entrancePosition;

	@ApiModelProperty(value = "使用状态（0：无 1：有）")
	private Integer useStatus;

	@ApiModelProperty(value = "管理员")
	private String manager;

	@ApiModelProperty(value = "联系手机")
	private String managerPhone;

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

	@ApiModelProperty(value = "出入口标识 0：入 1：出")
	@NotNull(message = "出入口标识不能为空",groups = {UpdateGroup.class, AddGroup.class})
	private Integer mask;

	@ApiModelProperty(value = "类别 0-小区出入口 1-停车场出入口")
	private Integer category;

	@ApiModelProperty(value = "出入口名称")
	@NotBlank(message = "出入口名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String entranceName;

	@ApiModelProperty(value = "所属停车场ID")
	private Long parkingId;

	@ApiModelProperty(value = "停车场分区ID")
	//@NotNull(message = "停车场分区ID不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Long parkingPartitionId;

	@ApiModelProperty(value = "停车场分区名称")
	private String parkingPartitionName;

	@ApiModelProperty(value = "设备imei")
	private String imei;

	@ApiModelProperty(value = "设备ID")
	private List<Long> deviceIds;

	@ApiModelProperty(value = "所属分区ID")
	private Long parentAreaId;
	@ApiModelProperty(value = "所属分区类型")
	private Integer areaType;







}