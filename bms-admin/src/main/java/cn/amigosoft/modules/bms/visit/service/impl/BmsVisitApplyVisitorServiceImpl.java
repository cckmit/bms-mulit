package cn.amigosoft.modules.bms.visit.service.impl;

import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyVisitorDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.bms.visit.service.BmsVisitApplyVisitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 人员报备访客关联表 
 */
@Service
public class BmsVisitApplyVisitorServiceImpl extends CrudServiceImpl<BmsVisitApplyVisitorDao, BmsVisitApplyVisitorEntity, BmsVisitApplyVisitorDTO> implements BmsVisitApplyVisitorService {

    @Override
    public QueryWrapper<BmsVisitApplyVisitorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<BmsVisitApplyVisitorEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }


}