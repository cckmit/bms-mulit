/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.message.service.impl;

import cn.amigosoft.modules.message.dao.SysMailTemplateDao;
import cn.amigosoft.modules.message.dto.SysMailTemplateDTO;
import cn.amigosoft.modules.message.email.EmailUtils;
import cn.amigosoft.modules.message.entity.SysMailTemplateEntity;
import cn.amigosoft.modules.message.service.SysMailTemplateService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysMailTemplateServiceImpl extends CrudServiceImpl<SysMailTemplateDao, SysMailTemplateEntity, SysMailTemplateDTO> implements SysMailTemplateService {

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public QueryWrapper<SysMailTemplateEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<SysMailTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);

        return wrapper;
    }

    @Override
    public boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception {
        Map<String, Object> map = null;
        try {
            if (StringUtils.isNotEmpty(params)) {
                map = JSON.parseObject(params, Map.class);
            }
        } catch (Exception e) {
            throw new RenException(ErrorCode.JSON_FORMAT_ERROR);
        }
        String[] to = new String[]{mailTo};
        String[] cc = StringUtils.isBlank(mailCc) ? null : new String[]{mailCc};

        return emailUtils.sendMail(id, to, cc, map);
    }

}
