package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsVerificationStatisticsDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsVerificationStatisticsDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsVerificationStatisticsEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsVerificationStatisticsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核销统计表
 */
@Service
public class BmsVerificationStatisticsServiceImpl extends CrudServiceImpl<BmsVerificationStatisticsDao, BmsVerificationStatisticsEntity, BmsVerificationStatisticsDTO> implements BmsVerificationStatisticsService {

    @Override
    public QueryWrapper<BmsVerificationStatisticsEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsVerificationStatisticsEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }

    @Override
    public List<BmsVerificationStatisticsEntity> selectDataFromCardLog(String importCode) {
        return baseDao.selectDataFromCardLog(importCode);
    }

    @Override
    public int deleteDuplicateData() {
        return baseDao.deleteDuplicateData();
    }

    @Override
    public Result<PageData<BmsVerificationStatisticsDTO>> getPage(Map<String, Object> params) {
        IPage<BmsVerificationStatisticsEntity> page = getPage(params, "v.create_date", false);
        List<BmsVerificationStatisticsDTO> list = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsVerificationStatisticsDTO>>().ok(getPageData(list, page.getTotal(), BmsVerificationStatisticsDTO.class));
    }

    @Override
    public List<BmsVerificationStatisticsDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public int updateStatus() {
        return baseDao.updateStatus();
    }

    @Override
    public int batchInsertVerificationStatistics(List<BmsVerificationStatisticsEntity> list, Long creator, Date createDate) {
        return baseDao.batchInsertVerificationStatistics(list,creator,createDate);
    }

    @Override
    public int updateOrderDetailId() {
        return baseDao.updateOrderDetailId();
    }

}
