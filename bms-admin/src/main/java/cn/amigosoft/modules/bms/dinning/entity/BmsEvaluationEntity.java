package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评价表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_evaluation")
public class BmsEvaluationEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评价主题
     */
    private String title;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 评价食堂ID
     */
    private Long canteenId;


    /**
     * 可参与评价的部门ID
     */
    private String deptIdList;

    /**
     * 可参与评价的员工ID
     */
    private String userIdList;

    /**
     * 富文本
     */
    private String richText;

    /**
     * 封图
     */
    private String img;

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

    /**
     * 评价食堂
     */
    @TableField(exist = false)
    private String canteenName;

}