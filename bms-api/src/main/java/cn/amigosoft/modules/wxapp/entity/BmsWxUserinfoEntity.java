package cn.amigosoft.modules.wxapp.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 微信用户信息
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("bms_wx_userinfo")
public class BmsWxUserinfoEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String unionId;

	/**
	 * 0：小程序 1：公众号 2：开放平台
	 */
	private Integer type;
    /**
     * 小程序用户用户唯一标识
     */
	private String openId;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 手机
     */
	private String mobile;
    /**
     * 头像
     */
	private String avatarUrl;
    /**
     * 省市
     */
	private String provice;
    /**
     * 城市
     */
	private String city;
    /**
     * 县区
     */
	private String country;
    /**
     * 性别
     */
	private Integer gender;
    /**
     * 语言
     */
	private String language;
    /**
     * 更新者
     */
	private Long updater;
    /**
     * 更新时间
     */
	private Date updateDate;

	/**
	 * 用户ID
	 */
	private Long userId;

}