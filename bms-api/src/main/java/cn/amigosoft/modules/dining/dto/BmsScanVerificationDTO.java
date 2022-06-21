package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 扫码核销表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@Data
@ApiModel(value = "扫码核销表")
public class BmsScanVerificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "订单ID")
	private Long orderId;

	@ApiModelProperty(value = "订单详情ID")
	private Long orderDetailId;

	@ApiModelProperty(value = "食堂ID")
	private Long canteenId;

	@ApiModelProperty(value = "扫码记录表ID")
	private Long scanLogId;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新人")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;


}