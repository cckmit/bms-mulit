package cn.amigosoft.modules.bms.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 访问地点用户关联表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_visit_address_user")
public class BmsVisitAddressUserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 访问地点ID
     */
    private Long addressId;

    /**
     * 用户ID
     */
    private Long userId;

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
