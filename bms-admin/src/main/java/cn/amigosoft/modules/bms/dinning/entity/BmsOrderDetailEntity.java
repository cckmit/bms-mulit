package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 订单详情表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_order_detail")
public class BmsOrderDetailEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 餐品ID
     */
    private Long itemId;
    /**
     * 食堂ID
     */
    private Long canteenId;
    /**
     * 餐别ID
     */
    private Long mealTypeId;

    /**
     * 用餐日期
     */
    private String eatDate;
    /**
     * 状态 （0：未核销 1：已核销）
     */
    private Integer status;
    /**
     * 扫码核销状态 （0：未核销 1：已核销）
     */
    private Integer scanStatus;
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
