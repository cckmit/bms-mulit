package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsReceptionMealVisitorDao;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVisitorDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVisitorEntity;
import cn.amigosoft.modules.dining.service.BmsReceptionMealVisitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 接待餐访客关联表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Service
public class BmsReceptionMealVisitorServiceImpl extends CrudServiceImpl<BmsReceptionMealVisitorDao, BmsReceptionMealVisitorEntity, BmsReceptionMealVisitorDTO> implements BmsReceptionMealVisitorService {

    @Override
    public QueryWrapper<BmsReceptionMealVisitorEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsReceptionMealVisitorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}