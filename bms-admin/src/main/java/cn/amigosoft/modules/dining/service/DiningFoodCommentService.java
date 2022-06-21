package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  每日菜品评价 服务接口类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 15:11:09
 */
public interface DiningFoodCommentService extends CrudService<DiningFoodCommentEntity, DiningFoodCommentDTO> {
    //评论分页
    PageData<DiningFoodCommentPageDTO> getCommentPage(Map<String, Object> params);
    //列表导出
    List<DiningFoodCommentPageDTO> getCommentList(Map<String, Object> params);
}

