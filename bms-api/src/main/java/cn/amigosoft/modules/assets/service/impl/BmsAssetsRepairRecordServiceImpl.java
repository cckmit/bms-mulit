package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dao.BmsAssetsRepairDao;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.assets.dao.BmsAssetsRepairRecordDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairRecordEntity;
import cn.amigosoft.modules.assets.service.BmsAssetsRepairRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产维修记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Service
public class BmsAssetsRepairRecordServiceImpl extends CrudServiceImpl<BmsAssetsRepairRecordDao, BmsAssetsRepairRecordEntity, BmsAssetsRepairRecordDTO> implements BmsAssetsRepairRecordService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsAssetsRepairDao assetsRepairDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsAssetsRepairRecordEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsAssetsRepairRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    /* 新增审批记录 */
    @Override
    public Result addVerifyRecord(BmsAssetsRepairRecordDTO dto) {
        Long assetsRepairId = dto.getAssetsRepairId();
        BmsAssetsRepairEntity data = assetsRepairDao.selectById(assetsRepairId);
        if (data == null) {
            return new Result().error("该报修记录不存在");
        }
        Long toUserId = dto.getToUserId();
        Long dbCreator = data.getCreator();
        Integer option = dto.getStatus();
        Integer status = null;
        // 若审批意见为0同意，则报修状态转为2待处理（已审批）
        if (BmsConstant.VERIFY_OPINION_APPROVE.equals(option)) {
            status = BmsConstant.REPAIR_VERIFY;
            wxService.sendWeixinTemplateMsg(dbCreator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",审批已通过", "pages/assets/repair/applyList", "已通过");
            //wxService.sendWeixinTemplateMsg(toUserId, "尊敬的用户，您有新的【报修审批】待处理", "请到平台进行审批", "pages/assets/deal/dealList", "待审批");
            List<SysUserDTO> list = userDao.selectUserByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR_DEAL);
            for (SysUserDTO userDTO : list) {
                wxService.sendWeixinTemplateMsg(userDTO.getId(), "尊敬的用户，您有新的【报修审批】待处理", "请到平台进行审批", "pages/assets/deal/dealList", "待处理");
            }
        }
        // 若审批意见为1驳回，则报修状态转为1已驳回
        if (BmsConstant.VERIFY_OPINION_REJECT.equals(option)) {
            status = BmsConstant.REPAIR_REJECT;
            wxService.sendWeixinTemplateMsg(dbCreator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",审批已驳回", "pages/assets/repair/applyList", "已驳回");
        }
        BmsAssetsRepairRecordEntity entity = new BmsAssetsRepairRecordEntity();
        entity.setAssetsRepairId(assetsRepairId);
        entity.setStatus(status);
        entity.setContent(dto.getContent());
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        entity.setCreator(creator);
        entity.setCreateDate(new Date());
        baseDao.insert(entity);

        BmsAssetsRepairEntity repairEntity = new BmsAssetsRepairEntity();
        repairEntity.setId(assetsRepairId);
        repairEntity.setStatus(status);
        repairEntity.setDealUserId(toUserId);
        assetsRepairDao.updateById(repairEntity);
        return new Result();
    }

    /* 报修处理 */
    @Override
    public Result addDealRecord(BmsAssetsRepairRecordDTO dto) {
        Long assetsRepairId = dto.getAssetsRepairId();
        BmsAssetsRepairEntity checkEntity = assetsRepairDao.selectById(assetsRepairId);
        // 检查当前的报修申请是否还是待处理状态，若不是，则为已被其它物业人员处理
        if (!BmsConstant.REPAIR_VERIFY.equals(checkEntity.getStatus())) {
            return new Result().error("该申请已被处理");
        }

        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        Integer status = null;

        BmsAssetsRepairRecordEntity entity = new BmsAssetsRepairRecordEntity();
        BmsAssetsRepairEntity repairEntity = new BmsAssetsRepairEntity();

        // 直接处理
        if (!StringUtils.isBlank(dto.getContent())) {
            status = BmsConstant.REPAIR_PENDING_EVALUATE;     //状态将变为4待评价
            entity.setIsDispatch(BmsConstant.DISPATCH_NO);   //直接处理： 0
            entity.setContent(dto.getContent());  //处理内容
            repairEntity.setIsDispatch(BmsConstant.DISPATCH_NO);   //直接处理： 0
            repairEntity.setDealUserId(userId);  // 处理人为本人
            repairEntity.setDealResult(dto.getContent()); //处理结果
            wxService.sendWeixinTemplateMsg(checkEntity.getCreator(), "尊敬的用户，您的【报修】进度更新了", checkEntity.getTitle() + ",已维修完毕", "pages/assets/repair/applyList", "待评价");
        }
        // 派单
        if (dto.getToUserId() != null) {
            status = BmsConstant.REPAIR_PENDING_DEAL;      //状态将变为3处理中
            entity.setIsDispatch(BmsConstant.DISPATCH_YES);  //转派： 1
            entity.setToUserId(dto.getToUserId());  // 被指派用户
            repairEntity.setIsDispatch(BmsConstant.DISPATCH_YES);  //转派： 1
            repairEntity.setDealUserId(dto.getToUserId());  //处理人为选择的维修人员
            wxService.sendWeixinTemplateMsg(checkEntity.getCreator(), "尊敬的用户，您的【报修】进度更新了", checkEntity.getTitle() + ",已指派维修", "pages/assets/repair/applyList", "处理中");
            wxService.sendWeixinTemplateMsg(dto.getToUserId(), "尊敬的用户，您有新的【报修】待处理", checkEntity.getTitle() + ",请尽快维修", "pages/assets/servicer/serviceList", "待维修");
        }
        // 新增维修记录
        entity.setAssetsRepairId(assetsRepairId);
        entity.setStatus(status);
        entity.setCreator(userId);
        entity.setCreateDate(new Date());
        baseDao.insert(entity);

        // 更新维修表
        repairEntity.setId(assetsRepairId);
        repairEntity.setStatus(status);
        repairEntity.setUpdater(userId);
        repairEntity.setUpdateDate(new Date());
        assetsRepairDao.updateById(repairEntity);
        return new Result();
    }

    /* 获取内部维修人员列表 */
    @Override
    public List<SysUserDTO> getRepairServiceList(Map<String, Object> params) {
        params.put("permission", BmsConstant.PERMISSION_ASSETS_REPAIR_REPAIR);
        //List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR_REPAIR);
        List<SysUserDTO> list = userDao.getServiceListByPermission(params);
        return list;
    }

    /* 获取外部维修人员列表 */
    @Override
    public List<SysUserDTO> getExternalServiceList(Map<String, Object> params) {
        params.put("permission", BmsConstant.PERMISSION_ASSETS_REPAIR_EXTERNAL_REPAIR);
        //List<SysUserDTO> list = userDao.getExServiceListByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR_EXTERNAL_REPAIR);
        List<SysUserDTO> list = userDao.getExServiceListByPermission(params);
        return list;
    }
}