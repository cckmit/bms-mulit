package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.assets.dao.AssetsRepairDealDao;
import cn.amigosoft.modules.assets.dto.AssetsRepairDealDTO;
import cn.amigosoft.modules.assets.entity.AssetsRepairDealEntity;
import cn.amigosoft.modules.assets.service.AssetsRepairDealService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  资产维修处理表 服务实现类
 * </p>
 *
 * @author : hupishi
 * @version : 1.0
 * @date : 2021-06-01 15:20:45
 */
@Service
public class AssetsRepairDealServiceImpl extends CrudServiceImpl<AssetsRepairDealDao, AssetsRepairDealEntity, AssetsRepairDealDTO> implements AssetsRepairDealService {

    @Override
    public QueryWrapper<AssetsRepairDealEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String tenantId = (String)params.get("tenantId");
        String assetsRepairId = (String)params.get("assetsRepairId");
        String toPersonId = (String)params.get("toPersonId");
        String status = (String)params.get("status");
        String result = (String)params.get("result");
        String imgs = (String)params.get("imgs");
        String remark = (String)params.get("remark");
        String creator = (String)params.get("creator");
        String createDate = (String)params.get("createDate");
        String updater = (String)params.get("updater");
        String updateDate = (String)params.get("updateDate");
        QueryWrapper<AssetsRepairDealEntity> wrapper = new QueryWrapper<>();
  
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(tenantId), "tenant_id", tenantId);
        wrapper.eq(StringUtils.isNotBlank(assetsRepairId), "assets_repair_id", assetsRepairId);
        wrapper.eq(StringUtils.isNotBlank(toPersonId), "to_person_id", toPersonId);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);
        wrapper.eq(StringUtils.isNotBlank(result), "result", result);
        wrapper.eq(StringUtils.isNotBlank(imgs), "imgs", imgs);
        wrapper.eq(StringUtils.isNotBlank(remark), "remark", remark);
        wrapper.eq(StringUtils.isNotBlank(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotBlank(createDate), "create_date", createDate);
        wrapper.eq(StringUtils.isNotBlank(updater), "updater", updater);
        wrapper.eq(StringUtils.isNotBlank(updateDate), "update_date", updateDate);
        return wrapper;
    }

}

