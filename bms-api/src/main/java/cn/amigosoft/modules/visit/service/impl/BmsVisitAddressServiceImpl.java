package cn.amigosoft.modules.visit.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.visit.dao.BmsVisitAddressDao;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitAddressEntity;
import cn.amigosoft.modules.visit.service.BmsVisitAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 访问地点表 
 */
@Service
public class BmsVisitAddressServiceImpl extends CrudServiceImpl<BmsVisitAddressDao, BmsVisitAddressEntity, BmsVisitAddressDTO> implements BmsVisitAddressService {

    @Override
    public QueryWrapper<BmsVisitAddressEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<BmsVisitAddressEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public List<BmsVisitAddressDTO> getChooseList() {
        List<BmsVisitAddressDTO> list = baseDao.getChooseList();
        for (BmsVisitAddressDTO dto : list) {
            dto.setChecked(false);
        }
        return list;
    }
}