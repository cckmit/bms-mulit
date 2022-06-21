package cn.amigosoft.modules.area.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用于查询社区楼栋、楼层、房间、停车位等等数量信息
 */
@Data
@Accessors(chain = true)
public class AreaCountInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋数量
     */
    private Integer buildingCount;

    /**
     * 楼层数量
     */
    private Integer floorCount;

    /**
     * 房屋数量
     */
    private Integer roomCount;

    /**
     * 住户数量
     */
    private Integer persionCount;

    /**
     * 	车位数量
     */
    private Integer carSpaceCount;

    /**
     * 车量数量
     */
    private Integer carCount;

}


