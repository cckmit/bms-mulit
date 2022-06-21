package cn.amigosoft.modules.assets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 资产维修记录表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Data
@ApiModel(value = "资产维修记录表 ")
public class BmsAssetsRepairRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "维修表ID(bms_assets_repair)")
	private Long assetsRepairId;

	@ApiModelProperty(value = "被指派用户ID(sys_user)")
	private Long toUserId;

	@ApiModelProperty(value = "处理状态 （0：待审批 1：已驳回 2：待处理（已审批） 3：处理中 4：待评价 5：已完成）")
	private Integer status;

	@ApiModelProperty(value = "处理内容")
	private String content;

	@ApiModelProperty(value = "是否派单 （0:直接处理 1:指派他人）")
	private Integer isDispatch;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识 （0：未删除；1：已删除）")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建者(sys_user)")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "创建人姓名")
	private String creatorName;

	@ApiModelProperty(value = "创建人工号")
	private String creatorWorkNo;

	@ApiModelProperty(value = "被指派用户姓名")
	private String toUserName;

	@ApiModelProperty(value = "被指派用户工号")
	private String toUserWorkNo;
}