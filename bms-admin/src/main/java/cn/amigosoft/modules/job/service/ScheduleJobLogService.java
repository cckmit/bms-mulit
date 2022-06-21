/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.job.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.modules.job.dto.ScheduleJobLogDTO;
import cn.amigosoft.modules.job.entity.ScheduleJobLogEntity;
import cn.amigosoft.common.service.BaseService;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

    PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

    ScheduleJobLogDTO get(Long id);

}
