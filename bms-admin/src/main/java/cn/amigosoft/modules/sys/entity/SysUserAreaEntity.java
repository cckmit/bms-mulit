package cn.amigosoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户与区域关系表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_area")
public class SysUserAreaEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 区域ID
     */
    private Long areaId;
    /**
     * 租户ID
     */
    private Long tenantId;

}