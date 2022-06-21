package cn.amigosoft.modules.area.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ChenXiong
 * @Description: 设备信息
 * @CreateTime: 2020年7月9日11:34:00
 */
@Data
@Accessors(chain = true)
public class AreaBriefInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域图片
     */
    private String areaImg;

    /**
     * 区域类型
     */
    private Integer areaType;

    /**
     * 楼栋层数
     */
    private Integer floorNum;

    /**
     * 楼栋房间数
     */
    private Integer roomNum;

    /**
     * 住户数量
     */
    private Integer residentNum;

    /**
     * 停车位数量
     */
    private Integer parkNum;

    /**
     * 管理员姓名
     */
    private String managerName;

    /**
     * 管理员联系电话
     */
    private String managerPhone;

    /**
     * 出入口标识 0：出入 1：出 ，2：入
     */
    private Integer entranceMask;

    /**
     * 地址
     */
    private String address;
}
