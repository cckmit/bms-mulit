package cn.amigosoft.modules.assets.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@Data
public class AssetsEquipmentExcel {
    @Excel(name = "资产编码",width = 20)
    private String code;
    @Excel(name = "资产名称")
    private String name;
    @Excel(name = "设备编号")
    private String equipmentNo;
    @Excel(name = "资产类别")
    private String assetsType;
    @Excel(name = "设备厂商")
    private String vendor;
    @Excel(name = "规格型号")
    private String model;
    @Excel(name = "所属区域")
    private String areaName;
    @Excel(name = "安装位置")
    private String position;
    @Excel(name = "管理部门")
    private String dept;
    @Excel(name = "责任人")
    private String liabilityPerson;
    @Excel(name = "当前使用人")
    private String currentPerson;
    @Excel(name = "购置日期",format = "yyyy-MM-dd",width = 15)
    private Date purchaseTime;
    @Excel(name = "购置金额", suffix = "元")
    private BigDecimal purchaseAmount;
    @Excel(name = "启用日期",format = "yyyy-MM-dd",width = 15)
    private Date enableTime;
    @Excel(name = "使用年限", suffix = "年")
    private Integer useLimit;
    private String completeAcceptance;
    @Excel(name = "资产状态",replace = {"在用_1","闲置_2","报废_3","_null"})
    private Integer status;
    @Excel(name = "维修状态",replace = {"正常_0","维修中_1","_null"})
    private Integer maintainStatus;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date createDate;

}