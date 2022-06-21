/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.message.service;

import cn.amigosoft.modules.message.dto.SysSmsDTO;
import cn.amigosoft.modules.message.entity.SysSmsEntity;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.BaseService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysSmsService extends BaseService<SysSmsEntity> {

    PageData<SysSmsDTO> page(Map<String, Object> params);

    /**
     * 发送短信
     * @param mobile   手机号
     * @param params   短信参数
     */
    void send(String mobile, String params);

    /**
     * 保存短信发送记录
     * @param platform   平台
     * @param mobile   手机号
     * @param params   短信参数
     * @param status   发送状态
     */
    void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status);

}

