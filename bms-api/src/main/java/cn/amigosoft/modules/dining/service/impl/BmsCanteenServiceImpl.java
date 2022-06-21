package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsCanteenDao;
import cn.amigosoft.modules.dining.dto.BmsCanteenDTO;
import cn.amigosoft.modules.dining.entity.BmsCanteenEntity;
import cn.amigosoft.modules.dining.service.BmsCanteenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 食堂表
 */
@Service
public class BmsCanteenServiceImpl extends CrudServiceImpl<BmsCanteenDao, BmsCanteenEntity, BmsCanteenDTO> implements BmsCanteenService {

    @Override
    public QueryWrapper<BmsCanteenEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsCanteenEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del", "0");
        return wrapper;
    }


}