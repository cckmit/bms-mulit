package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsDishesTypeDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesTypeDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesTypeEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsDishesTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 菜品类别表 
 */
@Service
public class BmsDishesTypeServiceImpl extends CrudServiceImpl<BmsDishesTypeDao, BmsDishesTypeEntity, BmsDishesTypeDTO> implements BmsDishesTypeService {

    @Override
    public QueryWrapper<BmsDishesTypeEntity> getWrapper(Map<String, Object> params){
        String name = (String)params.get("name");

        QueryWrapper<BmsDishesTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        return wrapper;
    }


}