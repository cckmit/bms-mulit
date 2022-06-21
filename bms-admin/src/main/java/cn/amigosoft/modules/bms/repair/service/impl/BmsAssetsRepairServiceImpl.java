package cn.amigosoft.modules.bms.repair.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsRepairDao;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsRepairRecordDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairRecordEntity;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsRepairService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资产维修表
 */
@Service
public class BmsAssetsRepairServiceImpl extends CrudServiceImpl<BmsAssetsRepairDao, BmsAssetsRepairEntity, BmsAssetsRepairDTO> implements BmsAssetsRepairService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsAssetsRepairRecordDao assetsRepairRecordDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsAssetsRepairEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsAssetsRepairEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result<PageData<BmsAssetsRepairDTO>> getPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        IPage<BmsAssetsRepairEntity> page = getPage(params, "r.create_date", false);
        List<BmsAssetsRepairDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsRepairDTO>>().ok(getPageData(resultList, page.getTotal(), BmsAssetsRepairDTO.class));
    }

    @Override
    public Result<PageData<BmsAssetsRepairDTO>> getVerifyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        int verifyCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_VERIFY);
        int dealCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_DEAL);
        List<Integer> statusList = new ArrayList<>();
        if (verifyCount > 0) {
            // 审核人员能看到待审批数据
            statusList.add(BmsConstant.REPAIR_WAIT);
            if (dealCount == 0) {
                // 没有处理权限只能看到自己的数据
                params.put("toUserId", userId);
            }
        }
        if (dealCount > 0) {
            // 处理人员能看到待处理数据
            statusList.add(BmsConstant.REPAIR_APPROVE);
        }
        params.put("statusList", statusList);
        IPage<BmsAssetsRepairEntity> page = getPage(params, "r.create_date", false);
        List<BmsAssetsRepairDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsRepairDTO>>().ok(getPageData(resultList, page.getTotal(), BmsAssetsRepairDTO.class));
    }

    @Override
    public Result<PageData<BmsAssetsRepairDTO>> getRecordPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("record", "record");
        params.put("recordUserId", userId);
        int dealCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_DEAL);
        if (dealCount > 0) {
            params.put("isProperty", "isProperty");
        }
        IPage<BmsAssetsRepairEntity> page = getPage(params, "r.create_date", false);
        List<BmsAssetsRepairDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsRepairDTO>>().ok(getPageData(resultList, page.getTotal(), BmsAssetsRepairDTO.class));
    }

    @Override
    public Result<PageData<BmsAssetsRepairDTO>> getStatisticsPage(Map<String, Object> params) {
        IPage<BmsAssetsRepairEntity> page = getPage(params, "r.create_date", false);
        List<BmsAssetsRepairDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsRepairDTO>>().ok(getPageData(resultList, page.getTotal(), BmsAssetsRepairDTO.class));
    }

    @Override
    public BmsAssetsRepairDTO selectAssetsRepairById(Long id) {
        BmsAssetsRepairDTO data = baseDao.selectAssetsRepairById(id);
        List<BmsAssetsRepairRecordDTO> recordList = assetsRepairRecordDao.selectAssetsRepairRecordList(id);
        data.setRecordList(recordList);
        return data;
    }


    @Override
    public void insertAssetsRepair(BmsAssetsRepairDTO dto) {
        BmsAssetsRepairEntity assetsRepairEntity = ConvertUtils.sourceToTarget(dto, BmsAssetsRepairEntity.class);
        baseDao.insert(assetsRepairEntity);
        BmsAssetsRepairRecordEntity recordEntity = new BmsAssetsRepairRecordEntity();
        Long dealUserId = dto.getDealUserId();
        recordEntity.setAssetsRepairId(assetsRepairEntity.getId());
        recordEntity.setToUserId(dealUserId);
        recordEntity.setStatus(BmsConstant.REPAIR_WAIT);
        assetsRepairRecordDao.insert(recordEntity);
        UserDetail user = SecurityUser.getUser();
        wxService.sendWeixinTemplateMsg(dealUserId, "尊敬的用户，您有新的【报修审批】待处理", user.getRealName() + "向您提交了报修申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
    }

    @Override
    public List<BmsAssetsRepairDTO> selectApplyExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        return baseDao.selectExportList(params);
    }

    @Override
    public List<BmsAssetsRepairDTO> selectVerifyExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        int verifyCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_VERIFY);
        int dealCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_DEAL);
        List<Integer> statusList = new ArrayList<>();
        if (verifyCount > 0) {
            statusList.add(BmsConstant.REPAIR_WAIT);
            if (dealCount == 0) {
                params.put("toUserId", userId);
            }
        }
        if (dealCount > 0) {
            statusList.add(BmsConstant.REPAIR_APPROVE);
        }
        params.put("statusList", statusList);
        return baseDao.selectExportList(params);
    }

    @Override
    public List<BmsAssetsRepairDTO> selectRecordExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("record", "record");
        params.put("recordUserId", userId);
        int dealCount = userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ASSETS_REPAIR_DEAL);
        if (dealCount > 0) {
            params.put("isProperty", "isProperty");
        }
        return baseDao.selectExportList(params);
    }

    @Override
    public List<BmsAssetsRepairDTO> selectStatisticsExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);

    }

}
