package cn.amigosoft.modules.area.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.area.dao.BmsAreaDao;
import cn.amigosoft.modules.area.dto.BmsAreaDTO;
import cn.amigosoft.modules.area.entity.BmsAreaEntity;
import cn.amigosoft.modules.area.service.BmsAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 区域表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-18
 */
@Service
public class BmsAreaServiceImpl extends CrudServiceImpl<BmsAreaDao, BmsAreaEntity, BmsAreaDTO> implements BmsAreaService {

    @Override
    public QueryWrapper<BmsAreaEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsAreaEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}