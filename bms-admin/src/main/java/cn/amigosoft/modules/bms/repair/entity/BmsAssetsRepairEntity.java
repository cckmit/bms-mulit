package cn.amigosoft.modules.bms.repair.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 资产维修表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_assets_repair")
public class BmsAssetsRepairEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报修资产ID(bms_assets)
     */
    private Long assetsId;

    /**
     * 报修资产编号(bms_assets)
     */
    private String assetsCode;

    /**
     * 报修资产名称
     */
    private String assetsName;

    /**
     * 报修区域
     */
    private String area;

    /**
     * 报修项目
     */
    private String title;

    /**
     * 报修图片
     */
    private String imgs;

    /**
     * 报修内容
     */
    private String content;

    /**
     * 处理状态 （0：待审批 1：已驳回 2：待处理（已审批） 3：处理中 4：待评价 5：已完成）
     */
    private Integer status;

    /**
     * 是否派单 （0：直接处理 1：指派他人）
     */
    private Integer isDispatch;

    /**
     * 处理人ID(sys_user_id)
     */
    private Long dealUserId;

    /**
     * 处理结果
     */
    private String dealResult;

    /**
     * 处理结果图片
     */
    private String repairImgs;

    /**
     * 维修结果评价
     */
    private String repairEvaluation;

    /**
     * 维修结果评分 （0-5对应0-5颗星）
     */
    private Integer repairGrade;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识
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