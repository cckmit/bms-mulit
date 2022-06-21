package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsScanVerificationDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsScanVerificationEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsScanVerificationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 扫码核销表
 */
@Service
public class BmsScanVerificationServiceImpl extends CrudServiceImpl<BmsScanVerificationDao, BmsScanVerificationEntity, BmsScanVerificationDTO> implements BmsScanVerificationService {

    @Override
    public QueryWrapper<BmsScanVerificationEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsScanVerificationEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }


    @Override
    public Result<PageData<BmsScanVerificationDTO>> getPage(Map<String, Object> params) {
        IPage<BmsScanVerificationEntity> page = getPage(params, "v.create_date", false);
        List<BmsScanVerificationDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsScanVerificationDTO>>().ok(getPageData(resultList, page.getTotal(), BmsScanVerificationDTO.class));
    }

    @Override
    public List<BmsScanVerificationDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

}
