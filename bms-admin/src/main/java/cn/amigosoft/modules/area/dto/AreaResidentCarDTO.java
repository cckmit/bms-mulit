package cn.amigosoft.modules.area.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 车辆信息表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
@Data
@ApiModel(value = "车辆信息表 ")
public class AreaResidentCarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "车辆ID")
	private Long id;

	@ApiModelProperty(value = "车主类型(1:住户，2：临时，3:黑名单)")
	private Integer personType;

	@ApiModelProperty(value = "车主ID")
	private Long personId;

	@ApiModelProperty(value = "车位ID")
	private Long areaParkingSpaceId;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "车牌号")
	private String carCode;

	@ApiModelProperty(value = "车牌省份(例：闽)")
	private String carProvince;

	@ApiModelProperty(value = "车牌城市(例：A)")
	private String carCity;

	@ApiModelProperty(value = "车牌类型(1:大,2:中,3:小)")
	private Integer carSize;

	@ApiModelProperty(value = "车牌照片")
	private String carImg;

	@ApiModelProperty(value = "车主电话")
	private String phone;

	@ApiModelProperty(value = "有效期")
	private Date endTime;

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