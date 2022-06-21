package cn.amigosoft.modules.bms.dinning.dto;

import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 评价表 
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

	@ApiModelProperty(value = "评价食堂")
	private String canteenName;

	@ApiModelProperty(value = "富文本")
	private String richText;

	@ApiModelProperty(value = "封图")
	private String img;

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

	@ApiModelProperty(value = "发布人员")
	private String publisher;

	@ApiModelProperty(value = "员工工号")
	private String publisherWorkNo;

	@ApiModelProperty(value = "可参与评价的部门ID")
	private String deptIdList;

	@ApiModelProperty(value = "可参与评价的员工ID")
	private String userIdList;

	@ApiModelProperty(value = "可参与评价的员工信息")
	private List<Map<String, String>> userList;

	@ApiModelProperty(value = "可参与评价的部门信息")
	private List<SysDeptDTO> deptList;
}