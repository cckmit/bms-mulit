package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 节假日表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_holiday")
public class BmsHolidayEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 假日
     */
    private String holiday;
    /**
     * 备注
     */
    private String remark;
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
