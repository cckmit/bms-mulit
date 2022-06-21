package cn.amigosoft.modules.wxapp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 小程序二维码生成
 */
@Data
@ApiModel(value = "小程序二维码生成 ")
public class WxappQrcodeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "租户id")
	private Long tenantId;

	@ApiModelProperty(value = "业务类型(10：小程序-总主页，20：访客-子主页，21：访客-预约物业页)")
	private Integer businessType;

	@ApiModelProperty(value = "参数")
	private String scene;

	@ApiModelProperty(value = "小程序打开地址")
	private String page;

	@ApiModelProperty(value = "二维码宽度(最小 280px，最大 1280px)")
	private Integer width;

	@ApiModelProperty(value = "二维码图片")
	private String qcodeUrl;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;


}
