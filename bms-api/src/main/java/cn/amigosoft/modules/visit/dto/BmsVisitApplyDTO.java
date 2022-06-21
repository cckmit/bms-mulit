package cn.amigosoft.modules.visit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 人员报备申请表 
 */
@Data
@ApiModel(value = "人员报备申请表 ")
public class BmsVisitApplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "被访人ID")
	private Long userId;

	@ApiModelProperty(value = "访问事项")
	private String matter;

	@ApiModelProperty(value = "访问开始时间")
	private Date beginDate;

	@ApiModelProperty(value = "访问结束时间")
	private Date endDate;

	@ApiModelProperty(value = "访问地点ID bms_visit_address表")
	private Long addressId;

	@ApiModelProperty(value = "审批人员ID sys_user表")
	private Long verifyUserId;

	@ApiModelProperty(value = "状态 （0：待审批 1：同意 2：拒绝）")
	private Integer status;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建人openId")
	private String creatorOpenId;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新人")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "被访人姓名")
	private String realName;

	@ApiModelProperty(value = "联系电话")
	private String mobile;

	@ApiModelProperty(value = "审批人员姓名")
	private String verifyUser;

	@ApiModelProperty(value = "审批人员工号")
	private String verifyWorkNo;

	@ApiModelProperty(value = "访问地点")
	private String addressName;

	@ApiModelProperty(value = "访客名称（逗号分隔")
	private String visitors;

	/*@ApiModelProperty(value = "回复意见")
	private String replyContent;*/

	/*@ApiModelProperty(value = "状态值")
	private String statusStr;*/

	@ApiModelProperty(value = "访客列表")
	private List<BmsVisitApplyVisitorDTO> visitorDTOList;

	@ApiModelProperty(value = "访问地点ID列表")
	private List<Long> addressIds;

	@ApiModelProperty(value = "审批流程")
	private List<BmsVisitApplyVerifyDTO> verifyList;

	@ApiModelProperty(value = "当前登录用户ID")
	private Long currentUserId;

}