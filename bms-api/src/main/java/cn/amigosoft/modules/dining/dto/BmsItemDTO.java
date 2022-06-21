package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 餐品表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Data
@ApiModel(value = "餐品表 ")
public class BmsItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "餐品名称")
    private String name;

    @ApiModelProperty(value = "餐品类型 （0：套餐 1：自助餐）")
    private Integer type;

    @ApiModelProperty(value = "餐品类型")
    private String typeName;

    @ApiModelProperty(value = "餐别ID")
    private Long mealTypeId;

    @ApiModelProperty(value = "餐别")
    private String mealTypeName;

    @ApiModelProperty(value = "供应食堂ID")
    private Long canteenId;

    @ApiModelProperty(value = "供应食堂")
    private String canteenName;

    @ApiModelProperty(value = "餐品图片")
    private String img;

    @ApiModelProperty(value = "富文本")
    private String richtext;

    @ApiModelProperty(value = "（0：上线 1：下线）")
    private Integer status;

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

    @ApiModelProperty(value = "关联菜品")
    private List<Map<String, String>> dishesList;

    @ApiModelProperty(value = "关联菜品ID")
    private String dishesIds;

    @ApiModelProperty(value = "餐品详情名称")
    private String dishesName;

    @ApiModelProperty(value = "是否已选择")
    private Boolean selected;

    @ApiModelProperty(value = "菜品图片")
    private List<String> dishesImgList;

    @ApiModelProperty(value = "用餐开始时间")
    private String beginTime;

}