/**
 * Copyright (c) 2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */
package cn.amigosoft.modules.notice.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.notice.entity.SysNoticeUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 我的通知
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysNoticeUserDao extends BaseDao<SysNoticeUserEntity> {

    /**
     * 通知全部用户
     */
    void insertAllUser(SysNoticeUserEntity entity);

    /**
     * 未读的通知数
     * @param receiverId  接收者ID
     */
    int getUnReadNoticeCount(Long receiverId);
}