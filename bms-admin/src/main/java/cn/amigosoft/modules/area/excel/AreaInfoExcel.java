package cn.amigosoft.modules.area.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 区域详细信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
public class AreaInfoExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "区域ID")
    private Long areaId;
    @Excel(name = "租户ID")
    private Long tenantId;
    @Excel(name = "区域名称")
    private String areaName;
    @Excel(name = "区域编号")
    private String areaCode;
    @Excel(name = "区域类型（1小区、2商场）")
    private Integer areaType;
    @Excel(name = "省份")
    private String provinceCode;
    @Excel(name = "城市")
    private String cityCode;
    @Excel(name = "县区")
    private String countyCode;
    @Excel(name = "所属街道")
    private String street;
    @Excel(name = "详细地址")
    private String address;
    @Excel(name = "街道办电话")
    private String streetOfficePhone;
    @Excel(name = "楼栋数量")
    private Integer buildingNum;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "维度")
    private String latitude;
    @Excel(name = "地图缩放等级")
    private Integer mapZoom;
    @Excel(name = "开发商")
    private String developer;
    @Excel(name = "所属派出所")
    private String policeStation;
    @Excel(name = "派出所电话")
    private String policeStationPhone;
    @Excel(name = "物业ID")
    private Long propertyId;
    @Excel(name = "创建者")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "更新者")
    private Long updater;
    @Excel(name = "更新时间")
    private Date updateDate;
    @Excel(name = "备注")
    private String note;
    @Excel(name = "是否删除（0未删除，1已删除）")
    private Integer del;

}