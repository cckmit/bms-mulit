package cn.amigosoft.modules.assets.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 资产设备分类表  实体类
 * </p>
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_assets_equipment_type")
public class AssetsEquipmentTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 上级ID，逗号分隔
     */
    private String pids;

    /**
     * 类别编号
     */
    private String typeNo;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别层级
     */
    private Integer level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除: 1、已删除  0、未删除
     */
    private Integer del;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateDate;

}
