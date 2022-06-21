package cn.amigosoft.modules.dining.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 每日菜品 实体类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 14:45:58
 */
@Data
public class DiningFoodDailyMenuExcel {

    private static final long serialVersionUID = 1L;

    @Excel(name = "菜单id")
    private Long id;

    @Excel(name = "租户Id")
    private Long tenantId;

    @Excel(name = "每日菜品日期 yyyy-mm-dd")
    private Date menuDate;

    @Excel(name = "菜品Id iotsaas_dining_foods_lib.id")
    private Long diningFoodLibId;

    @Excel(name = "每日菜品类型 1.早餐 2.午餐 3.晚餐")
    private Integer diningType;

    @Excel(name = "是否上架 0:否 1:是")
    private Integer status;

    @Excel(name = "创建者id")
    private Long creator;

    @Excel(name = "创建日期")
    private Date createDate;

    @Excel(name = "修改者 id")
    private Long updater;

    @Excel(name = "修改日期")
    private Date updateDate;

}
