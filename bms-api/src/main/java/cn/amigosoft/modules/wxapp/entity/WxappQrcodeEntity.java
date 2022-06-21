package cn.amigosoft.modules.wxapp.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 小程序二维码生成
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("iotsaas_wxapp_qrcode")
public class WxappQrcodeEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 业务类型(10：小程序-总主页，20：访客-子主页，21：访客-预约物业页)
     */
	private Integer businessType;
    /**
     * 参数
     */
	private String scene;
    /**
     * 小程序打开地址
     */
	private String page;
    /**
     * 二维码宽度(最小 280px，最大 1280px)
     */
	private Integer width;
    /**
     * 二维码图片
     */
	private String qcodeUrl;
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

	private Long tenantId;
}
