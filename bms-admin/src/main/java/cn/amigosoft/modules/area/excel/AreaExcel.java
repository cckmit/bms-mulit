package cn.amigosoft.modules.area.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 区域表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
public class AreaExcel {
    @Excel(name = "社区名称")
    private String areaName;

    @Excel(name = "楼栋名称")
    private String buildingName;

    @Excel(name = "楼层(请填数字,最大支持1000层)")
    private String floorNumber;

    @Excel(name = "房间名称(长度最大支持10位)")
    private String roomName;

    @Excel(name = "错误信息")
    private String errorMsg;
}