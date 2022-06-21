package cn.amigosoft.modules.dining.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p>
 * 菜品库表 实体类
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
@Data
public class DiningFoodLibExcel {

    private static final long serialVersionUID = 1L;

    @Excel(name = "菜品名称")
    private String name;

    @Excel(name = "菜品类型")
    private String type;

    @Excel(name = "餐厅名称")
    private String diningRoomName;

    @Excel(name = "菜品金额")
    private Double price;

    @Excel(name = "菜品描述")
    private String remark;

//    @Excel(name = "是否长期上架")
//    private String isEnduring;

    @Excel(name = "就餐时段")
    private String diningType;

//    @Excel(name = "循环周期")
//    private String week;

    @Excel(name = "菜品图片",type = 2 ,savePath = "upload\\")
    private String imgFile;

    @Excel(name = "错误提示")
    private String errorImport;
}
