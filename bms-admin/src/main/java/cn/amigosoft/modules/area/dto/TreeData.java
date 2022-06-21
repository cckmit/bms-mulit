package cn.amigosoft.modules.area.dto;

import cn.amigosoft.common.utils.TreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端 <a-tree>接收数据
 * 2020年7月8日17:07:25
 */
@Data
@Accessors(chain = true)
public class TreeData extends TreeNode<TreeData> {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * 节点类型
     */
    private String type;

    /**
     * 是否叶子节点
     */
    boolean leaf;
    /**
     * 添加备注
     */
    private String note;
    /**
     * 添加人ID
     */
    private Long userId;
    /**
     * 添加人姓名
     */
    private String userName;

}


