package cn.amigosoft.modules.bms.repair.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资产维修记录表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_assets_repair_record")
public class BmsAssetsRepairRecordEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 维修表ID(bms_assets_repair)
     */
    private Long assetsRepairId;

    /**
     * 被指派用户ID(sys_user)
     */
    private Long toUserId;

    /**
     * 处理状态 （0：待审批 1：已驳回 2：待处理（已审批） 3：处理中 4：待评价 5：已完成）
     */
    private Integer status;

    /**
     * 处理内容
     */
    private String content;

    /**
     * 是否派单 （0:直接处理 1:指派他人）
     */
    private Integer isDispatch;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识 （0：未删除；1：已删除）
     */
    private Integer del;

    /**
     * 租户ID
     */
    private Long tenantId;

}