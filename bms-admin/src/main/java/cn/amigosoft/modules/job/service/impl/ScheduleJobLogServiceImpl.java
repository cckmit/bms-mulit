/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.job.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.modules.job.dao.ScheduleJobLogDao;
import cn.amigosoft.modules.job.dto.ScheduleJobLogDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.modules.job.entity.ScheduleJobLogEntity;
import cn.amigosoft.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
        IPage<ScheduleJobLogEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );
        return getPageData(page, ScheduleJobLogDTO.class);
    }

    private QueryWrapper<ScheduleJobLogEntity> getWrapper(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");

        QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(jobId), "job_id", jobId);

        return wrapper;
    }

    @Override
    public ScheduleJobLogDTO get(Long id) {
        ScheduleJobLogEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, ScheduleJobLogDTO.class);
    }

}