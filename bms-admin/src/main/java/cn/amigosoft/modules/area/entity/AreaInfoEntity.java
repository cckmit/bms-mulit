package cn.amigosoft.modules.area.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 区域详细信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("iotsaas_area_info")
public class AreaInfoEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
	private Long areaId;

    /**
     * 区域名称
     */
	private String areaName;
    /**
     * 区域编号
     */
	private String areaCode;
    /**
     * 区域类型（1小区、2商场）
     */
	private Integer areaType;
    /**
     * 省份
     */
	private String provinceCode;
    /**
     * 城市
     */
	private String cityCode;
    /**
     * 县区
     */
	private String countyCode;
    /**
     * 所属街道
     */
	private String street;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 街道办电话
     */
	private String streetOfficePhone;

	/**
	 * 物业公司名称
	 */
	private String propertyCompanyName;

	/**
	 * 物业公司电话
	 */
	private String propertyCompanyPhone;

	/**
	 * 物业公司负责人
	 */
	private String propertyCompanyDuty;
    /**
     * 楼栋数量
     */
	private Integer buildingNum;
    /**
     * 经度
     */
	private String longitude;
    /**
     * 维度
     */
	private String latitude;
    /**
     * 地图缩放等级
     */
	private Integer mapZoom;
    /**
     * 开发商
     */
	private String developer;
    /**
     * 所属派出所
     */
	private String policeStation;
    /**
     * 派出所电话
     */
	private String policeStationPhone;
	/**
	 * 物业ID
	 */
	private Long propertyId;
    /**
     * 更新者
     */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
    /**
     * 更新时间
     */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
    /**
     * 备注
     */
	private String note;
    /**
     * 是否删除（0未删除，1已删除）
     */
	private Integer del;
}