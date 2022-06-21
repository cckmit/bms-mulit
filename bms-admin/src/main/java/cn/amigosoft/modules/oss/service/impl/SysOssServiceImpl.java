/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.oss.service.impl;

import cn.amigosoft.modules.oss.dao.SysOssDao;
import cn.amigosoft.modules.oss.entity.SysOssEntity;
import cn.amigosoft.modules.oss.service.SysOssService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageData<SysOssEntity> page(Map<String, Object> params) {
        IPage<SysOssEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                new QueryWrapper<>()
        );
        return getPageData(page, SysOssEntity.class);
    }

}
