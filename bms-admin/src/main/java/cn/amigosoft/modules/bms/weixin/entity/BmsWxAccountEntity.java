package cn.amigosoft.modules.bms.weixin.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 平台帐号信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_wx_account")
public class BmsWxAccountEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 类别 0-小程序 1-公众号 2-开放平台
     */
    private Integer type;
    /**
     * 小程序AppID
     */
    private String appId;
    /**
     * 小程序AppSecret
     */
    private String appSecret;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 访问令牌生成时间
     */
    private Date tokenCreateTime;
    /**
     * 访问令牌失效时间
     */
    private Date tokenInvalidTime;
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

    /**
     * 此社区是否拥有访客机设备(0没有，1有)
     */
    private Integer hasVisitorMachine;
}
