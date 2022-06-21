package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.sys.dao.SysUserAreaDao;
import cn.amigosoft.modules.sys.dto.SysUserAreaDTO;
import cn.amigosoft.modules.sys.entity.SysUserAreaEntity;
import cn.amigosoft.modules.sys.service.SysUserAreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户与区域关系表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-23
 */
@Service
public class SysUserAreaServiceImpl extends CrudServiceImpl<SysUserAreaDao, SysUserAreaEntity, SysUserAreaDTO> implements SysUserAreaService {

    @Override
    public QueryWrapper<SysUserAreaEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SysUserAreaEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

}