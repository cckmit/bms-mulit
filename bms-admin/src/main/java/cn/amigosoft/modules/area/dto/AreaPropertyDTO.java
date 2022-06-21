package cn.amigosoft.modules.area.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 物业表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-19
 */
@Data
@ApiModel(value = "物业表 ")
public class AreaPropertyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "公司名称")
	private String companyName;

	@ApiModelProperty(value = "物业负责人")
	private String master;

	@ApiModelProperty(value = "物业电话")
	private String phone;

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