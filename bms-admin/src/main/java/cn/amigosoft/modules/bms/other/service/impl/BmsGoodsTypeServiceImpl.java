package cn.amigosoft.modules.bms.other.service.impl;

import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.other.dao.BmsGoodsTypeDao;
import cn.amigosoft.modules.bms.other.dto.BmsGoodsTypeDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsTypeEntity;
import cn.amigosoft.modules.bms.other.service.BmsGoodsTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 货品类别表
 */
@Service
public class BmsGoodsTypeServiceImpl extends CrudServiceImpl<BmsGoodsTypeDao, BmsGoodsTypeEntity, BmsGoodsTypeDTO> implements BmsGoodsTypeService {

    @Override
    public QueryWrapper<BmsGoodsTypeEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<BmsGoodsTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        return wrapper;
    }


}