package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodCommentEntity;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每日菜品评价 Dao 接口
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 15:11:09
 */
@Mapper
public interface DiningFoodCommentDao extends BaseDao<DiningFoodCommentEntity> {
    /**
     * 评论分页
     * @param params,page
     */
    @SqlParser(filter = true)
    List<DiningFoodCommentPageDTO> getCommentPage(@Param("page") IPage<DiningFoodCommentEntity> page, @Param("params") Map<String, Object> params);
    /**
     * 评论列表
     * @param params
     */
    @SqlParser(filter = true)
    List<DiningFoodCommentPageDTO> getCommentList(@Param("params") Map<String, Object> params);

}
