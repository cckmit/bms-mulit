package cn.amigosoft.modules.assets.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 资产维修表 实体类
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_assets_repair")
public class AssetsRepairEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 报修资产ID(iotsaas_assets_equipment)
     */
    private Long assetsEquipmentId;

    /**
     * 报修资产编码(iotsaas_assets_equipment)
     */
    private String assetsEquipmentCode;

    /**
     * 报修资产名称
     */
    private String assetsEquipmentName;

    /**
     * 报修资产类型
     */
    private String assetsEquipmentType;

    /**
     * 报修描述
     */
    private String content;

    /**
     * 报修图片
     */
    private String imgs;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 （0.待处理、1.处理中、2.待评价、3.已完成）
     */
    private Integer status;

    /**
     * 是否直接处理
     */
    private Integer isDispatch;
    /**
     * 处理人ID(iotsaas_person)
     */
    private Long dealPersonId;

    /**
     * 处理结果
     */
    private String dealResult;

    /**
     * 处理结果图片
     */
    private String dealImgs;

    /**
     * 处理时间
     */
    private Date dealDate;

    /**
     * 评价评分
     */
    private Integer grade;

    /**
     * 评价描述
     */
    private String gradeDesc;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

}
