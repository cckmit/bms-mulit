package cn.amigosoft.modules.bms.other.service.impl;

import cn.amigosoft.modules.bms.other.dao.BmsAreaDao;
import cn.amigosoft.modules.bms.other.dto.BmsAreaDTO;
import cn.amigosoft.modules.bms.other.entity.BmsAreaEntity;
import cn.amigosoft.modules.bms.other.service.BmsAreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 区域表 
 */
@Service
public class BmsAreaServiceImpl extends CrudServiceImpl<BmsAreaDao, BmsAreaEntity, BmsAreaDTO> implements BmsAreaService {

    @Override
    public QueryWrapper<BmsAreaEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<BmsAreaEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }


    @Override
    public List<BmsAreaDTO> selectAreaList() {
        return baseDao.selectAreaList();
    }
}