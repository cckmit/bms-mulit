/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.message.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.message.entity.SysMailTemplateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件模板
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysMailTemplateDao extends BaseDao<SysMailTemplateEntity> {
	
}
