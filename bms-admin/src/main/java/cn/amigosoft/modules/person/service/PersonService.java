package cn.amigosoft.modules.person.service;


import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.person.dto.PersonDTO;
import cn.amigosoft.modules.person.entity.PersonEntity;

/**
 * 人员基础信息表
 *
 * @author 刘宏涛 liuht@amigosoft.cn
 * @since 1.0.0 2020-07-23
 */
public interface PersonService extends CrudService<PersonEntity, PersonDTO> {

}