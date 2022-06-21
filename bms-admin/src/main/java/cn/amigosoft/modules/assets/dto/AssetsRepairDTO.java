package cn.amigosoft.modules.assets.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资产维修表 实体类
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
@ApiModel
@Data
public class AssetsRepairDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
	@NotNull(message = "报修资产Id不能为空",groups = { DefaultGroup.class })
	private Long id;

	@ApiModelProperty("租户ID")
	private Long tenantId;

	@ApiModelProperty("报修资产ID(iotsaas_assets_equipment)")
	@NotNull(message = "报修资产不能为空",groups = { AddGroup.class})
	private Long assetsEquipmentId;

	@ApiModelProperty("报修资产编码(iotsaas_assets_equipment)")
	@NotNull(message = "报修资产编码不能为空",groups = { AddGroup.class})
	private String assetsEquipmentCode;

	@ApiModelProperty("报修内容")
	@Length(max = 200,message = "报修内容长度不应超过200个字符",groups = { AddGroup.class})
	@Length(min = 1,message = "报修内容长度不应小于1个字符",groups = { AddGroup.class})
	private String content;

	@ApiModelProperty("报修图片")
	private String imgs;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
	private Integer status;

	@ApiModelProperty("处理人ID(iotsaas_person)")
	private Long dealPersonId;

	@ApiModelProperty("是否直接处理 0:直接处理 1:指派他人")
	@NotNull(message = "是否指派不能为空",groups = { UpdateGroup.class})
	private Integer isDispatch;

	@ApiModelProperty("处理结果")
	private String dealResult;

	@ApiModelProperty("处理结果图片")
	private String dealImgs;

	@ApiModelProperty("评价评分")
	@Max(value = 5,message = "评分数值应为1~5")
	@Min(value = 1,message = "评分数值应为1~5")
	private Integer grade;

	@ApiModelProperty("评价描述")
	@Length(max=200,message = "评价描述不应操作200字符")
	private String gradeDesc;

	@ApiModelProperty("评价图片")
	private String gradeImgs;

	@ApiModelProperty("创建者 （报修人）")
	private Long creator;

	@ApiModelProperty("创建时间 （报修时间）")
	private Date createDate;

	@ApiModelProperty("更新者")
	private Long updater;

	@ApiModelProperty("更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "报修项目")
	private String title;

	@ApiModelProperty(value = "报修类型")
	private Integer type;

	@ApiModelProperty(value = "报修描述")
	private String description;

	@ApiModelProperty(value = "报修人")
	private String initiator;

	@ApiModelProperty(value = "处理人")
	private String dealPerson;

	@ApiModelProperty(value = "处理时间")
	private Date dealTime;

}
