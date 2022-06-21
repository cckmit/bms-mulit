/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.message.service;

import cn.amigosoft.modules.message.dto.SysMailTemplateDTO;
import cn.amigosoft.modules.message.entity.SysMailTemplateEntity;
import cn.amigosoft.common.service.CrudService;

/**
 * 邮件模板
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMailTemplateService extends CrudService<SysMailTemplateEntity, SysMailTemplateDTO> {

    /**
     * 发送邮件
     * @param id           邮件模板ID
     * @param mailTo       收件人
     * @param mailCc       抄送
     * @param params       模板参数
     */
    boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception;

}