/**
 * Copyright (c) 2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */
package cn.amigosoft.modules.notice.dao;

import cn.amigosoft.modules.notice.entity.SysNoticeEntity;
import cn.amigosoft.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通知管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysNoticeDao extends BaseDao<SysNoticeEntity> {

    /**
     * 获取被通知的用户列表
     */
    List<SysNoticeEntity> getNoticeUserList(Map<String, Object> params);

    /**
     * 获取我的通知列表
     */
    List<SysNoticeEntity> getMyNoticeList(Map<String, Object> params);
}