package cn.amigosoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 部门职位表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept_position")
public class DeptPositionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 职位类型
     */
    private Integer positionType;
    /**
     * 职位名称
     */
    private String positionName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 备注
     */
    private String note;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 管理者姓名
     */
    @TableField(exist = false)
    private String realName;
    /**
     * 手机号
     */
    @TableField(exist = false)
    private String mobile;

}