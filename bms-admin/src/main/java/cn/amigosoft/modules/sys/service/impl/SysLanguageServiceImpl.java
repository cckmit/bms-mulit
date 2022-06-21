/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.sys.dao.SysLanguageDao;
import cn.amigosoft.modules.sys.entity.SysLanguageEntity;
import cn.amigosoft.modules.sys.service.SysLanguageService;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 国际化
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if (baseDao.getLanguage(entity) == null) {
            baseDao.insert(entity);
        } else {
            baseDao.updateLanguage(entity);
        }
    }

    @Override
    public void deleteLanguage(String tableName, Long tableId) {
        baseDao.deleteLanguage(tableName, tableId);
    }

}