package cn.amigosoft.modules.person.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.person.dao.PersonDao;
import cn.amigosoft.modules.person.dto.PersonDTO;
import cn.amigosoft.modules.person.entity.PersonEntity;
import cn.amigosoft.modules.person.service.PersonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 人员基础信息表
 *
 * @author 刘宏涛 liuht@amigosoft.cn
 * @since 1.0.0 2020-07-23
 */
@Service
public class PersonServiceImpl extends CrudServiceImpl<PersonDao, PersonEntity, PersonDTO> implements PersonService {

    @Override
    public QueryWrapper<PersonEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<PersonEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
}
