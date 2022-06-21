package cn.amigosoft.modules.dining.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p>
 * 每日菜品评价 实体类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 15:11:09
 */
@Data
public class DiningFoodCommentExcel {

    private static final long serialVersionUID = 1L;

    @Excel(name = "菜品编号")
    private String libNo;

    @Excel(name = "餐厅名称")
    private String diningRoomName;

    @Excel(name = "菜品名称")
    private String name;

    @Excel(name = "菜品日期")
    private String menuDateStr;

    @Excel(name = "就餐时段")
    private String diningTypeStr;

    @Excel(name = "评价总数")
    private Integer commentTotal;

    @Excel(name = "1星评价（数量/占比）")
    private String percentageByOne;

    @Excel(name = "2星评价（数量/占比）")
    private String percentageByTwo;

    @Excel(name = "3星评价（数量/占比）")
    private String percentageByThree;

    @Excel(name = "4星评价（数量/占比）")
    private String percentageByFour;

    @Excel(name = "5星评价（数量/占比）")
    private String percentageByFive;


}
