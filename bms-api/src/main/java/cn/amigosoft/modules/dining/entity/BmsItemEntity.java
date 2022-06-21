package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 餐品表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_item")
public class BmsItemEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 餐品名称
     */
    private String name;
    /**
     * 餐品类型 （0：套餐 1：自助餐）
     */
    private Integer type;
    /**
     * 餐别ID
     */
    private Long mealTypeId;
    /**
     * 供应食堂ID
     */
    private Long canteenId;
    /**
     * 餐品图片
     */
    private String img;
    /**
     * 富文本
     */
    private String richtext;
    /**
     * （0：上线 1：下线）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标识 （0：未删除 1：已删除）
     */
    private Integer del;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
}