package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 核销统计表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_verification_statistics")
public class BmsVerificationStatisticsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 日期
     */
    private String eatDate;
    /**
     * 餐别ID
     */
    private Long mealTypeId;
    /**
     * 报餐状态 （0：未报）
     */
    private Integer orderStatus;
    /**
     * 刷卡次数
     */
    private Integer swipeCardCount;
    /**
     * 消费金额
     */
    private String businessMoney;
    /**
     * 核销状态 （0：未核销 1：已核销）
     */
    private Integer status;

    /**
     * 订单详情ID
     */
    private Long orderDetailId;

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
