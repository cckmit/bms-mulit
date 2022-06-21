/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.common.handler;

import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 公共字段，自动填充值
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createDate";
    private final static String CREATOR = "creator";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATER = "updater";
    private final static String DEPT_ID = "deptId";
    private final static String TENANT_ID = "tenantId";


    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();

        //创建者
        Object creator = getFieldValByName(CREATOR, metaObject);
        if (creator == null) {
            setInsertFieldValByName(CREATOR, user.getId(), metaObject);
        }

        //创建时间
        Object createDate = getFieldValByName(CREATE_DATE, metaObject);
        if (createDate == null) {
            setInsertFieldValByName(CREATE_DATE, date, metaObject);
        }

        //创建者所属部门
        Object deptId = getFieldValByName(DEPT_ID, metaObject);
        if (deptId == null) {
            setInsertFieldValByName(DEPT_ID, user.getDeptId(), metaObject);
        }

        //创建者所属租户
        // setInsertFieldValByName(TENANT_ID, user.getTenantId(), metaObject);

        //更新者
        // setInsertFieldValByName(UPDATER, user.getId(), metaObject);

        //更新时间
        // setInsertFieldValByName(UPDATE_DATE, date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新者
        Object updater = getFieldValByName(UPDATER, metaObject);
        if (updater == null) {
            setUpdateFieldValByName(UPDATER, SecurityUser.getUserId(), metaObject);
        }

        //更新时间
        Object updateDate = getFieldValByName(UPDATE_DATE, metaObject);
        if (updateDate == null) {
            setUpdateFieldValByName(UPDATE_DATE, new Date(), metaObject);
        }
    }
}