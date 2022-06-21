package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsOrderDetailDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderDetailEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsOrderDetailService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表
 */
@Service
public class BmsOrderDetailServiceImpl extends CrudServiceImpl<BmsOrderDetailDao, BmsOrderDetailEntity, BmsOrderDetailDTO> implements BmsOrderDetailService {

    @Override
    public QueryWrapper<BmsOrderDetailEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsOrderDetailEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }

    @Override
    public Result<PageData<BmsOrderDetailDTO>> getPage(Map<String, Object> params) {
        IPage<BmsOrderDetailEntity> page = getPage(params, "od.create_date", false);
        List<BmsOrderDetailDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsOrderDetailDTO>>().ok(getPageData(resultList, page.getTotal(), BmsOrderDetailDTO.class));
    }

    @Override
    public List<BmsOrderDetailDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public int selectUserDetailCount(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        return baseDao.selectUserDetailCount(creator, params.get("eatDate"), params.get("mealTypeId"));
    }

    @Override
    public Result<PageData<BmsOrderDetailDTO>> getCountPage(Map<String, Object> params) {
        IPage<BmsOrderDetailEntity> page = getPage(params, "a.eatDate", false);
        List<BmsOrderDetailDTO> resultList = baseDao.selectCountPageList(page, params);
        return new Result<PageData<BmsOrderDetailDTO>>().ok(getPageData(resultList, page.getTotal(), BmsOrderDetailDTO.class));
    }

    @Override
    public List<BmsOrderDetailDTO> selectCountExportList(Map<String, Object> params) {
        return baseDao.selectCountExportList(params);
    }
}