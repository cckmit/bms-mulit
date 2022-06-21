package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 一卡通记录表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_card_log")
public class BmsCardLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private Long serialNum;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 个人编号
     */
    private String userCode;
    /**
     * 卡片类型
     */
    private String cardType;
    /**
     * 交易区号
     */
    private String businessArea;
    /**
     * 交易站点
     */
    private String businessSite;
    /**
     * 交易地点
     */
    private String businessAddress;
    /**
     * 终端编号
     */
    private String terminalCode;
    /**
     * 交易类型
     */
    private String businessType;
    /**
     * 交易钱包
     */
    private String businessPurse;
    /**
     * 交易金额
     */
    private String businessMoney;
    /**
     * 卡余额
     */
    private String cardBalance;
    /**
     * 库余额
     */
    private String stockBalance;
    /**
     * 钱包流水
     */
    private String purseSerial;
    /**
     * 交易时间
     */
    private String businessDate;
    /**
     * 到账时间
     */
    private String transferredDate;
    /**
     * 账户号
     */
    private String businessCode;
    /**
     * 商户名称
     */
    private String businessName;
    /**
     * 优惠金额
     */
    private String discountMoney;
    /**
     * 押金金额
     */
    private String deposit;
    /**
     * 卡费金额
     */
    private String cardFees;
    /**
     * 手续费用
     */
    private String charges;
    /**
     * 操作员号
     */
    private String operatorCode;
    /**
     * 卡户部门
     */
    private String accountDept;
    /**
     * 交易方式
     */
    private String transactionMode;
    /**
     * 支付渠道
     */
    private String paymentChannel;
    /**
     * 备注
     */
    private String remark;

    /**
     * 餐别ID
     */
    private Long mealTypeId;

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

    /**
     * 导入批次编号
     */
    private String importCode;

}