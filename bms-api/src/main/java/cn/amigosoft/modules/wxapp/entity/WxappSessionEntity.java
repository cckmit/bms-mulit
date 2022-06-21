package cn.amigosoft.modules.wxapp.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 小程序会话
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_wxapp_session")
public class WxappSessionEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String openId;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 过期时间
     */
    private Date expireDate;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateDate;

    private Long tenantId;
}
