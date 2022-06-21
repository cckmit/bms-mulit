package cn.amigosoft.modules.area.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 区域表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "区域表")
public class AreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "上级ID")
	private Long pid;

	@ApiModelProperty(value = "上级ID，逗号分隔")
	private String pids;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "区域图标")
	private String areaIcon;

	@ApiModelProperty(value = "区域图片")
	private String areaImg;

	@ApiModelProperty(value = "区域类型 小区 10000，楼栋    20000，楼层   20100，房间  20110，停车场   30000，分区   30100，车位  30110，出入口   40000，")
	private Integer areaType;

	@ApiModelProperty(value = "区域名称")

	private String areaName;

	@ApiModelProperty(value = "经度")
	private String longitude;

	@ApiModelProperty(value = "维度")
	private String latitude;

	@ApiModelProperty(value = "地图缩放等级")
	private Integer mapZoom;

	@ApiModelProperty(value = "是否使用（0：无 1：有）")
	private Integer useStatus;

	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "细分场景，用途细分场景使用，后续特定场景显示特定区域")
	private String scenes;

	@ApiModelProperty(value = "备注")
	private String note;

	@ApiModelProperty(value = "详细地址")
	private String address;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "是否删除（0未删除，1已删除）")
	private Integer del;


}