package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_food_daily_menu")
public class DiningFoodDailyMenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 每日菜品日期 yyyy-mm-dd
     */
    private Date menuDate;

    /**
     * 菜品Id iotsaas_dining_foods_lib.id
     */
    private Long diningFoodLibId;
    
    /**
     * 每日菜品类型 1.早餐 2.午餐 3.晚餐
     */
    private Integer diningType;
    
    /**
     * 是否上架 0:否 1:是
     */
    private Integer status;

    /**
     * 修改者 id
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    
    /**
     * 修改日期
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

}
