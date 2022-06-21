package cn.amigosoft.modules.area.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author
 * @Description:
 * @CreateTime:
 */
@Data
public class AreaFloorDetailDTO {

    /**
     * 楼栋名称
     */
    private String buildingName;

    /**
     * 楼层areaId
     */
    private Long floorAreaId;

    /**
     * 楼层图片
     */
    private String floorImg;

    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 房屋id、名称map
     */
    private Map<Long, String> roomMap;

    /**
     * 设备数
     */
    private Integer deviceNum;

    /**
     * 住户人数
     */
    private Integer residentNum;
}
