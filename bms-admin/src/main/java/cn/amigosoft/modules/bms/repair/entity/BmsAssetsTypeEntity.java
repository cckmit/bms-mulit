package cn.amigosoft.modules.bms.repair.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 资产类别表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_assets_type")
public class BmsAssetsTypeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 父级ID
     */
    private Long pid;
    /**
     * 上级ID（逗号分隔）
     */
    private String pids;
    /**
     * 类别编号
     */
    private String code;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别层级 （从1开始）
     */
    private Integer level;
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
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

}