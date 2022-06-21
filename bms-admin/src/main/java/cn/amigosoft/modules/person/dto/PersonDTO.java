package cn.amigosoft.modules.person.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 人员基础信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-08-01
 */
@Data
@ApiModel(value = "人员基础信息表")
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "iotsaas_tenant表ID租户id")
    private Long tenantId;

    @ApiModelProperty(value = "人员所属区域")
    private Long areaId;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "门禁人脸id")
    private String guardFaceImageId;

    @ApiModelProperty(value = "抓拍人脸id")
    private String faceImageId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "请输入姓名", groups = DefaultGroup.class)
    @Length(max = 20, groups = DefaultGroup.class, message = "超出最大长度")
    private String name;

    @ApiModelProperty(value = "手机号码")
    @Length(max = 11, message = "超出最大长度", groups = DefaultGroup.class)
    private String phone;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "证件类型 1：身份证、2：居住证、3：签证、4：护照、5：户口本、6：军人证  99：其他")
    private Integer licenseType;

    @ApiModelProperty(value = "证件号码")
    @Length(max = 18, groups = DefaultGroup.class, message = "超出最大长度")
    private String licenseNo;

    /**
     * 移动端用户id
     */
    private Long appUserId;

    @ApiModelProperty(value = "性别 0：男 1：女 2：保密")
    private Integer gender;

    @ApiModelProperty(value = "人员性质 1=住户,2=关注人员,3=访客")
    private Integer category;

    @ApiModelProperty(value = "人员类型 1:业主 2:租户 3:外卖 4:快递 5:装修 6:访客 99:其他")
    private Integer type;

    @ApiModelProperty(value = "0：非黑名单1：黑名单")
    private Integer blacklist;

    @ApiModelProperty(value = "category=1时为iotsaas_area_resident.id，category=2时为iotsaas_person_monitor.id")
    private Long objectId;

    @ApiModelProperty(value = "移动端标志(0:未开通,1:已开通,2:待审核,3:已拒绝)")
    private Integer mobileFlag;

    @ApiModelProperty(value = "后端标志(0:未开通,1:已开通,2:待审核,3:已拒绝)")
    private Integer webFlag;

    @ApiModelProperty(value = "删除删除 0-未删除 1-删除")
    private Integer del;

    @ApiModelProperty(value = "告警标识 0-不告警 1-告警")
    private Integer warnFlag;

    @ApiModelProperty(value = "备注")
    @Length(max = 100, groups = DefaultGroup.class, message = "超出最大长度")
    private String remark;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

}
