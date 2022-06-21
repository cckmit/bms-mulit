package cn.amigosoft.modules.assets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 资产维修表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Data
@ApiModel(value = "资产维修表 ")
public class BmsAssetsRepairDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "报修资产ID(bms_assets)")
	private Long assetsId;

	@ApiModelProperty(value = "报修资产编号(bms_assets)")
	private String assetsCode;

	@ApiModelProperty(value = "报修资产名称")
	private String assetsName;

	@ApiModelProperty(value = "报修区域")
	private String area;

	@ApiModelProperty(value = "报修项目")
	private String title;

	@ApiModelProperty(value = "报修图片")
	private String imgs;

	@ApiModelProperty(value = "报修内容")
	private String content;

	@ApiModelProperty(value = "处理状态 （0：待审批 1：已驳回 2：待处理（已审批） 3：处理中 4：待评价 5：已完成）")
	private Integer status;

	@ApiModelProperty(value = "是否派单 （0：直接处理 1：指派他人）")
	private Integer isDispatch;

	@ApiModelProperty(value = "处理人ID(sys_user_id)")
	private Long dealUserId;

	@ApiModelProperty(value = "处理结果")
	private String dealResult;

	@ApiModelProperty(value = "处理结果图片")
	private String repairImgs;

	@ApiModelProperty(value = "维修结果评价")
	private String repairEvaluation;

	@ApiModelProperty(value = "维修结果评分 （0-5对应0-5颗星）")
	private Integer repairGrade;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建者(报修人,sys_user_id)")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "报修人员")
	private String creatorName;

	@ApiModelProperty(value = "联系电话")
	private String creatorMobile;

	@ApiModelProperty(value = "报修人工号")
	private String creatorWorkNo;

	@ApiModelProperty(value = "维修人员维修状态")
	private Integer repairStatus;

	@ApiModelProperty(value = "维修记录列表")
	private List<BmsAssetsRepairRecordDTO> recordList;
}