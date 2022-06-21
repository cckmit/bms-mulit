package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 餐别表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_meal_type")
public class BmsMealTypeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 餐别名称
     */
    private String name;
    /**
     * 提前报餐时间 （单位：小时）
     */
    private Integer advanceOrderTime;
    /**
     * 用餐开始时间
     */
    private String beginTime;
    /**
     * 用餐结束时间
     */
    private String endTime;
    /**
     * 状态 （0：上线 1：下线）
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
