package cn.amigosoft.modules.assets.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("iotsaas_assets_equipment")
public class AssetsEquipmentEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 所属区域(iotsaas_area)
     */
	private Long areaId;
    /**
     * 责任人(iotsaas_person_staff)
     */
	private Long liabilityPersonStaffId;
    /**
     * 当前使用人(iotsaas_person_staff)
     */
	private Long currentPersonStaffId;
    /**
     * 管理部门(sys_dept)
     */
	private Long deptId;
    /**
     * 物联网设备关联ID(iotsaas_device)
     */
	private Long deviceId;
    /**
     * 资产类别(iotsaas_assets_equipment_type)
     */
	private Long assetsEquipmentTypeId;
    /**
     * 资产名称
     */
	private String name;
    /**
     * 资产编码
     */
	private String code;
    /**
     * 设备编号
     */
	private String equipmentNo;
    /**
     * 设备厂商
     */
	private String vendor;
    /**
     * 规格型号
     */
	private String model;
	/**
	 * 二维码标签
	 */
	private String qrCodeUrl;
	/**
     * 安装位置
     */
	private String position;
    /**
     * 购置日期
     */
	private Date purchaseTime;
    /**
     * 购置金额 （单位：分）
     */
	private BigDecimal purchaseAmount;
    /**
     * 启用时间
     */
	private Date enableTime;
    /**
     * 使用年限
     */
	private Integer useLimit;
    /**
     * 年限提醒开关 （0.打开、1.关闭）
     */
	private Integer yearsRemind;
    /**
     * 提前提醒天数
     */
	private Integer advanceNums;
    /**
     * 设备图片
     */
	private String imgs;
    /**
     * 说明书
     */
	private String manual;
    /**
     * 竣工验收报告
     */
	private String completeAcceptance;
    /**
     * 资产状态 （1.在用、2.闲置、3.报废）
     */
	private Integer status;
    /**
     * 维修状态 （0.正常、1.维修中）
     */
	private Integer maintainStatus;
    /**
     * 删除状态 （0.正常、1.删除）
     */
	private Integer del;
    /**
     * 备注
     */
	private String remark;
    /**
     * 更新者
     */
	private Long updater;
    /**
     * 更新时间
     */
	private Date updateDate;
}