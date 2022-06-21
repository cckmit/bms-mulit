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
 * 菜品库表 实体类
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_food_lib")
public class DiningFoodLibEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜品Id
     */
    private Long id;

    /**
     * 菜品编号
     */
    private String libNo;

    /**
     * 餐厅编号
     */
    private Long diningRoomId;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 菜品类型（1.荤菜 2.素菜 3.荤素混合 4.其他）
     */
    private Integer type;

    /**
     * 菜品金额
     */
    private Double price;

    /**
     * 菜品描述
     */
    private String remark;

    /**
     * 菜品图片 限1张
     */
    private String imageUrl;

    /**
     * 是否长期（1.是 2.否）
     */
    private Integer isEnduring;

    /**
     * 早午饭餐（当类型为长期菜品时可多选,以逗号分隔：1.早餐 2.午餐 3.晚餐）
     */
    private String diningType;

    /**
     * 周期几 当周期为长期菜品时可选：([1-7]周一-周日,以逗号分割)
     */
    private String week;

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

    /**
     * 状态是否删除
     */
    private Integer status;

}
