package cn.amigosoft.modules.person.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.person.entity.PersonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员基础信息表
 *
 * @author 刘宏涛 liuht@amigosoft.cn
 * @since 1.0.0 2020-07-23
 */
@Mapper
public interface PersonDao extends BaseDao<PersonEntity> {

}