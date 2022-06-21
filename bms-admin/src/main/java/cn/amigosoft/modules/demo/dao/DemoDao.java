/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.demo.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.demo.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface DemoDao extends BaseDao<DemoEntity> {

    List<DemoEntity> getList(Map<String, Object> params);

}
