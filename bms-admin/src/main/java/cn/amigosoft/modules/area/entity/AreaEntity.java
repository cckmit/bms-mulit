package cn.amigosoft.modules.area.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 区域表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_area")
public class AreaEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 上级ID，逗号分隔
     */
    private String pids;

    /**
     * 区域图标
     */
    private String areaIcon;
    /**
     * 区域图片
     */
    @TableField(strategy = FieldStrategy.IGNORED)
    private String areaImg;
    /**
     * 区域类型
     * 小区 10000，
     * 楼栋    20000，
     * 楼层   20100，
     * 房间  20110，
     * 停车场   30000，
     * 分区   30100，
     * 车位  30110，
     * 出入口   40000，
     */
    private Integer areaType;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 维度
     */
    private String latitude;
    /**
     * 地图缩放等级
     */
    private Integer mapZoom;
    /**
     * 是否使用（0：无 1：有）
     */
    private Integer useStatus;
    /**
     * 描述
     */
    private String description;
    /**
     * 细分场景，用途细分场景使用，后续特定场景显示特定区域
     */
    private String scenes;
    /**
     * 备注
     */
    private String note;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    /**
     * 是否删除（0未删除，1已删除）
     */
    private Integer del;
}