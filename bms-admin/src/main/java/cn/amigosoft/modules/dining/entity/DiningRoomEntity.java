package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 智慧餐厅 实体类
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_dining_room")
public class DiningRoomEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    private Long personStaffId;

    /**
     * 餐厅名称
     */
    private String name;

    /**
     * 常规座位数
     */
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer seats;

    /**
     * 最大接待量
     */
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer maxCapacity;

    /**
     * 房间所属区域id iotsaas_area.id
     */
    private Long areaId;

    /**
     * 房间所属楼栋id iotsaas_area_building.id
     */
    private Long areaBuildingId;

    /**
     * 房间所属楼层id iotsaas_area_floor.id
     */
    private Long areaFloorId;

    /**
     * 0 正常 1暂停
     */
    private Integer status;

    /**
     * 图片
     */
    private String img;

    /**
     * 排序
     */
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateDate;

}
