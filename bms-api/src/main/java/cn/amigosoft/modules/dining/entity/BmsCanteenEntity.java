package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 食堂表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_canteen")
public class BmsCanteenEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 食堂名称
     */
    private String name;
    /**
     * 二维码标签url
     */
    private String qrCodeUrl;
    /**
     * 联系人ID
     */
    private Long linkUserId;
    /**
     * 食堂地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标识
     */
    private Integer del;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
}