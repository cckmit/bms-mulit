package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.sys.dto.SysUserDTO;

import java.util.List;
import java.util.Map;

/**
 * 接待餐表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
public interface BmsReceptionMealService extends CrudService<BmsReceptionMealEntity, BmsReceptionMealDTO> {

    //接待餐列表分页查询
    PageData<BmsReceptionMealDTO> queryPage(Map<String, Object> params);

    //接待餐详情
    BmsReceptionMealDTO getDetail(Long id);

    //新增接待餐
    Result insertReception(BmsReceptionMealDTO dto);

    //删除接待餐
    Result deleteReception(Long id);

    //获取食堂列表
    List<BmsReceptionMealDTO> getCanteenList();

    //获取餐别列表
    List<BmsReceptionMealDTO> getMealTypeList();

    List<SysUserDTO> getManagerVerifyList();

    List<SysUserDTO> getOfficeVerifyList();

    // 审批列表分页查询
    PageData<BmsReceptionMealDTO> queryVerifyPage(Map<String, Object> params);

    // 审批已通过列表分页查询
    PageData<BmsReceptionMealDTO> queryCheckPage(Map<String, Object> params);
}