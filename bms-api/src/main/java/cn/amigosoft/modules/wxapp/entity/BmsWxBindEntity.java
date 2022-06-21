package cn.amigosoft.modules.wxapp.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 小程序绑定表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_wx_bind")
public class BmsWxBindEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * unionId
     */
    private String unionId;
    /**
     * openId
     */
    private String openId;
    /**
     * sessionKey
     */
    private String sessionKey;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 类别 0-小程序 1-公众号 2-开放平台
     */
    private Integer type;
    /**
     * token
     */
    private String token;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标识 （0：未删除 1：已删除）
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