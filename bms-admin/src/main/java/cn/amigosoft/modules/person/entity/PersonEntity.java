package cn.amigosoft.modules.person.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 人员基础信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iotsaas_person")
public class PersonEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 人员所属区域
     */
    private Long areaId;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 门禁人脸id
     */
    private String guardFaceImageId;
    /**
     * 抓拍人脸id
     */
    private String faceImageId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String phone;

    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 证件类型 1：身份证、2：居住证、3：签证、4：护照、5：户口本、6：军人证  99：其他
     */
    private Integer licenseType;
    /**
     * 证件号码
     */
    private String licenseNo;
    /**
     * 性别 0：男 1：女 2：保密
     */
    private Integer gender;
    /**
     * 人员性质 1=住户,2=关注人员,3=访客
     */
    private Integer category;
    /**
     * 人员类型 1:业主 2:租户 3:外卖 4:快递 5:装修 6:访客 99:其他
     */
    private Integer type;
    /**
     * 0：非黑名单1：黑名单
     */
    private Integer blacklist;
    /**
     * category=1时为iotsaas_area_resident.id，category=2时为iotsaas_person_monitor.id
     */
    private Long objectId;
    /**
     * 移动端标志(0不开通    1开通)
     */
    private Integer mobileFlag;
    /**
     * 删除删除 0-未删除 1-删除
     */
    private Integer del;
    /**
     * 告警标识 0-不告警 1-告警
     */
    private Integer warnFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    /**
     * 后端用户id
     */
    private Long sysUserId;

    /**
     * app用户id
     */
    private Long appUserId;

    /**
     * 后端标识 0不开通    1开通   2绑定
     */
    private Integer webFlag;
}
