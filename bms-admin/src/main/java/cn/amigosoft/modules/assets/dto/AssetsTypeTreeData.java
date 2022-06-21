package cn.amigosoft.modules.assets.dto;

import cn.amigosoft.common.utils.TreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AssetsTypeTreeData extends TreeNode<AssetsTypeTreeData> {

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
}
