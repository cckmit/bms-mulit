package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dao.BmsAssetsRepairRecordDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairRecordEntity;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.service.SysUserService;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.assets.dao.BmsAssetsRepairDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.assets.service.BmsAssetsRepairService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产维修表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Service
public class BmsAssetsRepairServiceImpl extends CrudServiceImpl<BmsAssetsRepairDao, BmsAssetsRepairEntity, BmsAssetsRepairDTO> implements BmsAssetsRepairService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsAssetsRepairRecordDao assetsRepairRecordDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserService userService;


    @Override
    public QueryWrapper<BmsAssetsRepairEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsAssetsRepairEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    /* 申请人获取申请记录 */
    @Override
    public PageData<BmsAssetsRepairDTO> queryPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        IPage<BmsAssetsRepairEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsRepairDTO> list = baseDao.queryPage(params);
        return getPageData(list, page.getTotal(), BmsAssetsRepairDTO.class);
    }

    /* 审批人员获取审批记录 */
    public PageData<BmsAssetsRepairDTO> queryVerifyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("toUserId", userId);
        IPage<BmsAssetsRepairEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsRepairDTO> list = baseDao.queryVerifyPage(params);
        return getPageData(list, page.getTotal(), BmsAssetsRepairDTO.class);
    }

    /* 物业获取维修管理（处理）记录 */
    @Override
    public PageData<BmsAssetsRepairDTO> queryDealPage(Map<String, Object> params) {
        IPage<BmsAssetsRepairEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsRepairDTO> list = baseDao.queryDealPage(params);
        return getPageData(list, page.getTotal(), BmsAssetsRepairDTO.class);
    }

    /* 维修人员获取维修记录 */
    @Override
    public PageData<BmsAssetsRepairDTO> queryServicePage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("toUserId", userId);
        IPage<BmsAssetsRepairEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsRepairDTO> list = baseDao.queryServicePage(params);
        for (BmsAssetsRepairDTO dto : list) {
            Integer status = dto.getStatus();
            // 若状态为4待评价，或5已完成，或状态为3处理中且当前处理人不是本人，则维修状态为1已处理
            if (BmsConstant.REPAIR_PENDING_EVALUATE.equals(status) || BmsConstant.REPAIR_COMPLETE.equals(status)
                    || (!dto.getDealUserId().equals(userId) && BmsConstant.REPAIR_PENDING_DEAL.equals(status))) {
                dto.setRepairStatus(BmsConstant.SERVICE_REPAIR);
            }
            // 若状态为3处理中，且当前处理人是本人，则维修状态为0待维修
            if (BmsConstant.REPAIR_PENDING_DEAL.equals(status) && dto.getDealUserId().equals(userId)) {
                dto.setRepairStatus(BmsConstant.SERVICE_REPAIR_PENDING);
            }
        }
        return getPageData(list, page.getTotal(), BmsAssetsRepairDTO.class);
    }

    /* 获取报修申请详情 */
    @Override
    public BmsAssetsRepairDTO getDetail(Long id) {
        BmsAssetsRepairDTO repairDTO = baseDao.getDetail(id);
        List<BmsAssetsRepairRecordDTO> recordDTOList = assetsRepairRecordDao.getRepairRecordList(id);
        repairDTO.setRecordList(recordDTOList);
        return repairDTO;
    }

    /* 维修人员获取维修详情 */
    @Override
    public BmsAssetsRepairDTO getServiceDetail(Long id) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        BmsAssetsRepairDTO repairDTO = baseDao.getDetail(id);
        List<BmsAssetsRepairRecordDTO> recordDTOList = assetsRepairRecordDao.getRepairRecordList(id);
        repairDTO.setRecordList(recordDTOList);
        // 若当前处理人为本人，且状态为3待处理，则维修状态为0待处理；其余情况均为1已处理
        if (repairDTO.getDealUserId().equals(userId)
                && BmsConstant.REPAIR_PENDING_DEAL.equals(repairDTO.getStatus())) {
            repairDTO.setRepairStatus(BmsConstant.SERVICE_REPAIR_PENDING);
        } else {
            repairDTO.setRepairStatus(BmsConstant.SERVICE_REPAIR);
        }
        return repairDTO;
    }

    /* 新增报修申请 */
    @Override
    public void addRepairApply(BmsAssetsRepairDTO dto) {
        BmsAssetsRepairEntity repairEntity = ConvertUtils.sourceToTarget(dto, BmsAssetsRepairEntity.class);
        baseDao.insert(repairEntity);
        BmsAssetsRepairRecordEntity recordEntity = new BmsAssetsRepairRecordEntity();
        recordEntity.setAssetsRepairId(repairEntity.getId());
        Long dealUserId = dto.getDealUserId();
        recordEntity.setToUserId(dealUserId);
        recordEntity.setStatus(BmsConstant.REPAIR_PENDING_VERIFY);    //0待审批
        assetsRepairRecordDao.insert(recordEntity);
        UserDetail user = SecurityUser.getUser();
        wxService.sendWeixinTemplateMsg(dealUserId, "尊敬的用户，您有新的【报修审批】待处理", user.getRealName() + "向您提交了报修申请，请到平台进行审批", "pages/assets/verify/verifyList", "待审批");
    }

    /* 报修结果提交 */
    @Override
    public Result serviceResult(BmsAssetsRepairDTO dto) {
        Long id = dto.getId();
        BmsAssetsRepairEntity data = baseDao.selectById(id);
        if (data == null) {
            return new Result().error("该报修记录不存在");
        }
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        Integer status = null;

        BmsAssetsRepairEntity repairEntity = new BmsAssetsRepairEntity();
        BmsAssetsRepairRecordEntity entity = new BmsAssetsRepairRecordEntity();
        // 直接维修
        if (!StringUtils.isBlank(dto.getDealResult()) && !StringUtils.isBlank(dto.getRepairImgs())) {
            status = BmsConstant.REPAIR_PENDING_EVALUATE;      //状态变为4待评价
            repairEntity.setDealUserId(userId);   //处理人为本人
            repairEntity.setDealResult(dto.getDealResult());   //处理结果
            repairEntity.setRepairImgs(dto.getRepairImgs());   //处理结果图片
            entity.setIsDispatch(BmsConstant.DISPATCH_NO);     //直接处理：0
            entity.setContent(dto.getDealResult());   //处理结果
            entity.setRemark("维修");
            wxService.sendWeixinTemplateMsg(dto.getCreator(), "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",已维修完毕", "pages/assets/repair/applyList", "待评价");
        }
        // 转派
        if (dto.getDealUserId() != null && dto.getDealUserId().equals(userId)) {
            return new Result().error("不能转派给本人");
        }
        if (dto.getDealUserId() != null) {
            status = BmsConstant.REPAIR_PENDING_DEAL;    // 状态仍为3处理中
            repairEntity.setDealUserId(dto.getDealUserId());  //处理人为选择的转派人
            entity.setIsDispatch(BmsConstant.DISPATCH_YES);    //转派： 1
            entity.setToUserId(dto.getDealUserId());
            entity.setRemark("转派");
            wxService.sendWeixinTemplateMsg(dto.getDealUserId(), "尊敬的用户，您有新的【报修】待处理", data.getTitle() + ",请尽快维修", "pages/assets/servicer/serviceList", "待维修");
        }
        // 更新维修表
        repairEntity.setId(id);
        repairEntity.setStatus(status);
        repairEntity.setUpdater(userId);
        repairEntity.setUpdateDate(new Date());
        baseDao.updateById(repairEntity);

        // 新增记录表
        entity.setAssetsRepairId(id);
        entity.setStatus(status);
        entity.setCreator(userId);
        entity.setCreateDate(new Date());
        assetsRepairRecordDao.insert(entity);

        return new Result();
    }

    /* 报修结果评价 */
    @Override
    public Result evaluation(BmsAssetsRepairDTO dto) {
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        Long id = dto.getId();
        String evaluation = dto.getRepairEvaluation();
        // 新增报修记录
        BmsAssetsRepairRecordEntity entity = new BmsAssetsRepairRecordEntity();
        entity.setAssetsRepairId(id);
        entity.setStatus(BmsConstant.REPAIR_COMPLETE);       //状态转为已完成
        entity.setContent(evaluation);   //处理内容，这里为评价内容
        entity.setCreator(userId);
        entity.setCreateDate(new Date());
        assetsRepairRecordDao.insert(entity);
        // 更新报修表
        BmsAssetsRepairEntity repairEntity = new BmsAssetsRepairEntity();
        repairEntity.setId(id);
        repairEntity.setStatus(BmsConstant.REPAIR_COMPLETE);    //状态转为已完成
        repairEntity.setRepairEvaluation(evaluation);
        repairEntity.setUpdater(userId);
        repairEntity.setUpdateDate(new Date());
        baseDao.updateById(repairEntity);
        return new Result();
    }

    /* 获取审核人员列表 */
    @Override
    public List<SysUserDTO> getVerifyList() {
        List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR_VERIFY);
        return list;
    }
}
