package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.sys.dao.DeptPositionDao;
import cn.amigosoft.modules.sys.dto.DeptPositionDTO;
import cn.amigosoft.modules.sys.entity.DeptPositionEntity;
import cn.amigosoft.modules.sys.service.DeptPositionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门职位表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-17
 */
@Service
public class DeptPositionServiceImpl extends CrudServiceImpl<DeptPositionDao, DeptPositionEntity, DeptPositionDTO> implements DeptPositionService {

    @Override
    public QueryWrapper<DeptPositionEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<DeptPositionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<DeptPositionDTO> getPositionList(Long id) {
        List<DeptPositionEntity> list = baseDao.getPositionList(id);
        List<DeptPositionDTO> dtoList = ConvertUtils.sourceToTarget(list, DeptPositionDTO.class);
        return dtoList;
    }

}