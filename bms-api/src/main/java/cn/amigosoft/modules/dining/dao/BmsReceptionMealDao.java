package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVisitorDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 接待餐表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Mapper
public interface BmsReceptionMealDao extends BaseDao<BmsReceptionMealEntity> {

    //接待餐列表条件查询
    List<BmsReceptionMealDTO> queryPage(Map<String, Object> params);

    //接待餐已同意列表条件查询
    List<BmsReceptionMealDTO> queryCheckPage(Map<String, Object> params);

    //接待餐详情
    BmsReceptionMealDTO getDetail(@Param("id") Long id);

    //接待餐访客
    List<BmsReceptionMealVisitorDTO> getVisitors(@Param("id") Long id);

    //接待餐用餐时段
    BmsReceptionMealDTO getEatTime(@Param("id") Long id);

    //删除接待餐关联的访客
    void deleteVisitors(@Param("id") Long id);

    //删除接待餐关联的审核记录
    void deleteVerifies(@Param("id") Long id);

    //获取食堂列表
    List<BmsReceptionMealDTO> getCanteenList();

    //获取餐别列表
    List<BmsReceptionMealDTO> getMealTypeList();

    List<BmsReceptionMealDTO> queryVerifyPage(@Param("page") IPage<BmsReceptionMealEntity> page, @Param("params") Map<String, Object> params);

    BmsReceptionMealDTO selectReceptionDetail(Long receptionMealId);

}