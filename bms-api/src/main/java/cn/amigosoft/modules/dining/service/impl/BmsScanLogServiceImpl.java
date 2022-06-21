package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.service.BmsOrderDetailService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsScanLogDao;
import cn.amigosoft.modules.dining.dto.BmsScanLogDTO;
import cn.amigosoft.modules.dining.entity.BmsScanLogEntity;
import cn.amigosoft.modules.dining.service.BmsScanLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 扫码记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
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

    @Override
    public BmsScanLogDTO addScanLog(BmsScanLogDTO dto) {
        BmsScanLogEntity entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        entity.setScanType(BmsConstant.DINING_ORDER_VERIFICATION);
        baseDao.insert(entity);
        dto.setId(entity.getId());
        return dto;
    }
}