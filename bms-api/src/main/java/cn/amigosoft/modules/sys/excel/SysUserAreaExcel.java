package cn.amigosoft.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户与区域关系表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-23
 */
@Data
public class SysUserAreaExcel {

    @Excel(name = "id")
    private Long id;
    @Excel(name = "用户id")
    private Long userId;
    @Excel(name = "区域ID")
    private Long areaId;
    @Excel(name = "租户ID")
    private Long tenantId;
    @Excel(name = "创建者")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;

}