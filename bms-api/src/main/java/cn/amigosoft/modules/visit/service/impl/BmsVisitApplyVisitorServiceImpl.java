package cn.amigosoft.modules.visit.service.impl;

import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyVisitorDao;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.visit.service.BmsVisitApplyVisitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // 获取历史访客列表
    @Override
    public List<BmsVisitApplyVisitorDTO> getVisitorHistoryList() {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        List<BmsVisitApplyVisitorDTO> list = baseDao.getVisitorHistoryList(userId);
        return list;
    }

    // 获取访客信息
    @Override
    public BmsVisitApplyVisitorDTO getVisitorInfo(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        BmsVisitApplyVisitorDTO dto = baseDao.getVisitorInfo(params);
        return dto;
    }
}