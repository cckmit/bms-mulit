package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 评价表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-18
 */
@Data
@ApiModel(value = "评价表 ")
public class BmsEvaluationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "评价主题")
	private String title;

	@ApiModelProperty(value = "结束时间")
	private Date endDate;

	@ApiModelProperty(value = "评价食堂ID")
	private Long canteenId;

	@ApiModelProperty(value = "评价食堂名称")
	private String canteenName;

	@ApiModelProperty(value = "富文本")
	private String richText;

	@ApiModelProperty(value = "封图")
	private String img;

	@ApiModelProperty(value = "所选择的可参与评价的部门ID,以逗号分割")
	private String deptIdList;

	@ApiModelProperty(value = "所选择的可参与评价的员工ID,以逗号分割")
	private String userIdList;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
	private Integer del;

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

	@ApiModelProperty(value = "评价状态 （0：待评价 1：已评价 2：已过期")
	private Integer status;

	@ApiModelProperty(value = "评价用户ID")
	private Long userId;

	@ApiModelProperty(value = "评价内容")
	private String content;
}