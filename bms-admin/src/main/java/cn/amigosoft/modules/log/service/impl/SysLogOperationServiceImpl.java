/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.log.service.impl;

import cn.amigosoft.modules.log.dao.SysLogOperationDao;
import cn.amigosoft.modules.log.dto.SysLogOperationDTO;
import cn.amigosoft.modules.log.entity.SysLogOperationEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.modules.log.service.SysLogOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationDao, SysLogOperationEntity> implements SysLogOperationService {

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        IPage<SysLogOperationEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<SysLogOperationEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDTO.class);
    }

    private QueryWrapper<SysLogOperationEntity> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");
        String creatorName = (String) params.get("creatorName");
        String ip = (String) params.get("ip");
        String operation = (String) params.get("operation");
        String createStartDate = (String) params.get("createStartDate");
        String createEndDate = (String) params.get("createEndDate");

        QueryWrapper<SysLogOperationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);
        wrapper.like(StringUtils.isNotBlank(creatorName), "creator_name", creatorName);
        wrapper.eq(StringUtils.isNotBlank(ip), "ip", ip);
        wrapper.like(StringUtils.isNotBlank(operation), "operation", operation);
        wrapper.ge(StringUtils.isNotBlank(createStartDate), "create_date", createStartDate);
        wrapper.le(StringUtils.isNotBlank(createEndDate), "create_date", createEndDate);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogOperationEntity entity) {
        insert(entity);
    }

}