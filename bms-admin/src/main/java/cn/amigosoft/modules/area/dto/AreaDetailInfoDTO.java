package cn.amigosoft.modules.area.dto;

import lombok.Data;

/**
 * @author
 * @Description:
 * @CreateTime:
 */
@Data
public class AreaDetailInfoDTO extends AreaCountInfoDTO {

    private Long areaId;

    private String areaImg;

    private Long areaInfoId;

    private String areaName;

    private String position;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String address;

    private String street;

    private String streetOfficePhone;

    private String policeStation;

    private String policeStationPhone;

    private String developer;

    private Long propertyId;

    private String master;

    private String companyName;

    private String companyPhone;

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

    private String longitude;

    private String latitude;
}
