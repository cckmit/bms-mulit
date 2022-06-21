/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.demo.service.impl;

import cn.amigosoft.modules.demo.dao.DemoDao;
import cn.amigosoft.modules.demo.dto.DemoDTO;
import cn.amigosoft.modules.demo.entity.DemoEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.annotation.DataFilter;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.modules.demo.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoDao, DemoEntity> implements DemoService {

    /**
     * mybatis数据权限演示
     */
    @Override
    @DataFilter(prefix = "and")
    public PageData<DemoDTO> page(Map<String, Object> params) {
        paramsToLike(params, "title");

        //分页
        IPage<DemoEntity> page = getPage(params, Constant.CREATE_DATE, false);

        //查询
        List<DemoEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), DemoDTO.class);
    }

//    /**
//     * mybatis-plus数据权限演示
//     */
//    @Override
//    @DataFilter
//    public PageData<NewsDTO> page(Map<String, Object> params) {
//        IPage<NewsEntity> page = baseDao.selectPage(
//            getPage(params, Constant.CREATE_DATE, false),
//            getWrapper(params)
//        );
//        return getPageData(page, NewsDTO.class);
//    }
//
//    private QueryWrapper<NewsEntity> getWrapper(Map<String, Object> params){
//        String title = (String)params.get("title");
//        String startDate = (String)params.get("startDate");
//        String endDate = (String)params.get("endDate");
//
//        QueryWrapper<NewsEntity> wrapper = new QueryWrapper<>();
//        wrapper.like(StringUtils.isNotBlank(title), "title", title);
//        wrapper.ge(StringUtils.isNotBlank(startDate),"pub_date", startDate);
//        wrapper.le(StringUtils.isNotBlank(endDate),"pub_date", endDate);
//
//        //数据过滤
//        wrapper.apply(params.get(Constant.SQL_FILTER) != null, params.get(Constant.SQL_FILTER).toString());
//
//        return wrapper;
//    }

    @Override
    public DemoDTO get(Long id) {
        DemoEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, DemoDTO.class);
    }

    @Override
    public void save(DemoDTO dto) {
        DemoEntity entity = ConvertUtils.sourceToTarget(dto, DemoEntity.class);

        insert(entity);
    }

    @Override
    public void update(DemoDTO dto) {
        DemoEntity entity = ConvertUtils.sourceToTarget(dto, DemoEntity.class);

        updateById(entity);
    }

}
