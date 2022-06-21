package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.modules.bms.dinning.dao.BmsScanLogDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsScanLogDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsScanLogEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsScanLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 扫码记录表
 */
@Service
public class BmsScanLogServiceImpl extends CrudServiceImpl<BmsScanLogDao, BmsScanLogEntity, BmsScanLogDTO> implements BmsScanLogService {

    @Override
    public QueryWrapper<BmsScanLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsScanLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}
