package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_food_comment")
public class DiningFoodCommentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 租户id
     */
    private Long tenantId;
    
    /**
     * 每日菜品id( iotsaas_dining_food_daily_menu.id)
     */
    private Long diningFoodDailyMenuId;
    
    /**
     * 菜品评分 值为（1-5）
     */
    private Integer score;
    
    /**
     * 评价人id sys_user.id
     */
    private Long reviewerUserId;
    
    /**
     * 评价内容
     */
    private String content;
    
    /**
     * 评价时间
     */
    private Date commentDate;

}
