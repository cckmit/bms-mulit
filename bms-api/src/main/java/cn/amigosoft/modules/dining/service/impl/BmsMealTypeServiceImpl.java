package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsMealTypeDao;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.dining.service.BmsMealTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 餐别表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-14
 */
@Service
public class BmsMealTypeServiceImpl extends CrudServiceImpl<BmsMealTypeDao, BmsMealTypeEntity, BmsMealTypeDTO> implements BmsMealTypeService {

    @Override
    public QueryWrapper<BmsMealTypeEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsMealTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del", "0");
        wrapper.orderByAsc("begin_time");
        return wrapper;
    }

}