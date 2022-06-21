/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.demo.service;

import cn.amigosoft.modules.demo.dto.DemoDTO;
import cn.amigosoft.modules.demo.entity.DemoEntity;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.BaseService;

import java.util.Map;

/**
 * 新闻
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface DemoService extends BaseService<DemoEntity> {

    PageData<DemoDTO> page(Map<String, Object> params);

    DemoDTO get(Long id);

    void save(DemoDTO dto);

    void update(DemoDTO dto);
}

