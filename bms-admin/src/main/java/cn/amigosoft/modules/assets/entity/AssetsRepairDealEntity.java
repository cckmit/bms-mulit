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
 * 资产维修处理表 实体类
 * </p>
 *
 * @author : hupishi
 * @version : 1.0
 * @date : 2021-06-01 15:20:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_assets_repair_deal")
public class AssetsRepairDealEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 租户ID
     */
    private Long tenantId;
    
    /**
     * 维修表ID(iotsaas_assets_repair)
     */
    private Long assetsRepairId;
    
    /**
     * 被转派用户ID(iotsaas_person)
     */
    private Long toPersonId;
    
    /**
     * 处理状态 （0.待处理、1.处理中、2.待评价、3.已完成）
     */
    private Integer status;
    
    /**
     * 处理内容
     */
    private String result;

    /**
     * 是否直接处理
     */
    private Integer isDispatch;

    /**
     * 处理图片
     */
    private String imgs;
    
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
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

}
