package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 一卡通记录表
 */
@Data
public class BmsCardLogExcel {
    @Excel(name = "流水号")
    private Long serialNum;
    @Excel(name = "账号")
    private String account;
    @Excel(name = "姓名")
    private String realName;
    @Excel(name = "个人编号")
    private String userCode;
    @Excel(name = "卡片类型")
    private String cardType;
    @Excel(name = "交易区号")
    private String businessArea;
    @Excel(name = "交易站点")
    private String businessSite;
    @Excel(name = "交易地点")
    private String businessAddress;
    @Excel(name = "终端编号")
    private String terminalCode;
    @Excel(name = "交易类型")
    private String businessType;
    @Excel(name = "交易钱包")
    private String businessPurse;
    @Excel(name = "交易金额")
    private String businessMoney;
    @Excel(name = "卡余额")
    private String cardBalance;
    @Excel(name = "库余额")
    private String stockBalance;
    @Excel(name = "钱包流水")
    private String purseSerial;
    @Excel(name = "交易时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm")
    private Date businessDate;
    @Excel(name = "到账时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm")
    private Date transferredDate;
    @Excel(name = "账户号")
    private String businessCode;
    @Excel(name = "商户名称")
    private String businessName;
    @Excel(name = "优惠金额")
    private String discountMoney;
    @Excel(name = "押金金额")
    private String deposit;
    @Excel(name = "卡费金额")
    private String cardFees;
    @Excel(name = "手续费用")
    private String charges;
    @Excel(name = "操作员号")
    private String operatorCode;
    @Excel(name = "卡户部门")
    private String accountDept;
    @Excel(name = "交易方式")
    private String transactionMode;
    @Excel(name = "支付渠道")
    private String paymentChannel;
    @Excel(name = "备注")
    private String remark;

}