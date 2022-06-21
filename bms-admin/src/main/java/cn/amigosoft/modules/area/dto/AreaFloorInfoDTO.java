package cn.amigosoft.modules.area.dto;

import lombok.Data;

/**
 * @author
 * @Description:
 * @CreateTime:
 */
@Data
public class AreaFloorInfoDTO {
    private Long id;

    private Long areaFloorId;

    private Long areaId;

    private String areaImg;

    private Long areaBuildingId;

    private String buildingCode;

    private Integer floorNum;

    private String floorName;
}
