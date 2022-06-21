package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsMealTypeDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsMealTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 餐别表
 */
@Service
public class BmsMealTypeServiceImpl extends CrudServiceImpl<BmsMealTypeDao, BmsMealTypeEntity, BmsMealTypeDTO> implements BmsMealTypeService {

    @Override
    public QueryWrapper<BmsMealTypeEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<BmsMealTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        wrapper.eq(BmsConstant.STATUS_COLUMN_NAME, BmsConstant.STATUS_ONLINE);
        wrapper.orderByAsc("begin_time");
        return wrapper;
    }

}
