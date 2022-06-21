package cn.amigosoft.modules.wxapp.entity;

import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName : WxUserEntity
 * @Description : 微信平台获取用户信息
 */
@Data
public class WxUserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String openId;
    /**
     * 目前只需要openId 后续业务场景需要可以对接到小程序后台接口进行用户信息同步
     */

    private Long tenantId;
}
