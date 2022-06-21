package cn.amigosoft.modules.bms.repair.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 资产表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_assets")
public class BmsAssetsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 资产名称
     */
    private String name;
    /**
     * 资产编号
     */
    private String code;
    /**
     * 资产类别ID(bms_assets_type)
     */
    private Long assetsTypeId;
    /**
     * 设备厂商
     */
    private String vendor;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 所在区域
     */
    private String position;
    /**
     * 设备图片
     */
    private String imgs;
    /**
     * 管理部门ID(sys_dept)
     */
    private Long deptId;
    /**
     * 负责人(sys_user_id)
     */
    private Long dutyUserId;
    /**
     * 启用日期
     */
    private String enableDate;
    /**
     * 使用年限
     */
    private Integer useLimit;
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