package cn.amigosoft.modules.visit.service.impl;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.visit.dao.BmsVisitAddressUserDao;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyDao;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyVerifyDao;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressUserDTO;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVerifyEntity;
import cn.amigosoft.modules.visit.service.BmsVisitApplyVerifyService;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 人员报备审批表
 */
@Service
public class BmsVisitApplyVerifyServiceImpl extends CrudServiceImpl<BmsVisitApplyVerifyDao, BmsVisitApplyVerifyEntity, BmsVisitApplyVerifyDTO> implements BmsVisitApplyVerifyService {

    @Autowired
    private BmsVisitApplyDao applyDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsVisitAddressUserDao addressUserDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsVisitApplyVerifyEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsVisitApplyVerifyEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result saveVerify(BmsVisitApplyVerifyDTO dto) {
        Long applyId = dto.getApplyId();
        QueryWrapper<BmsVisitApplyVerifyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_id", applyId);
        queryWrapper.eq("del", 0);
        List<BmsVisitApplyVerifyEntity> verifyEntityList = baseDao.selectList(queryWrapper);
        if (verifyEntityList.size() == 0) {
            return new Result().error("审批异常");
        }

        Integer level = dto.getLevel();
        BmsVisitApplyVerifyEntity lastVerifyData = verifyEntityList.get(verifyEntityList.size() - 1);
        Integer lastLevel = lastVerifyData.getLevel();
        if (level.equals(lastLevel)) {
            return new Result().error("请勿重复审批");
        }

        Integer option = dto.getStatus();
        //Long nextUserId = dto.getNextUserId();
        // 查询对应的申请单基本信息
        BmsVisitApplyDTO applyDTO = applyDao.getDetailNew(applyId);
        Long applyCreator = applyDTO.getCreator();
        if (applyCreator == null) {
            applyCreator = applyDTO.getUserId();
        }
        Long addressId = applyDTO.getAddressId();


        BmsVisitApplyEntity applyEntity = new BmsVisitApplyEntity();
        BmsVisitApplyVerifyEntity entity = new BmsVisitApplyVerifyEntity();
        BmsVisitAddressUserDTO addressUserDTO = addressUserDao.getVerifyUserId(addressId);
        // 一级审批
        if (BmsConstant.VISIT_FIR_LEVEL.equals(level)) {
            if (BmsConstant.VERIFY_OPINION_APPROVE.equals(option)) { // 若审批意见为同意
                entity.setStatus(BmsConstant.VERIFY_STATUS_APPROVE);
                // 获取访问地点关联的二级审批人员
                wxService.sendWeixinTemplateMsg(addressUserDTO.getUserId(), "尊敬的用户，您有新的【访客审批】待处理",
                        applyDTO.getRealName() + "向您提交了访客申请，请到平台进行审批",
                        "pages/visit/verify/verifyList", "待审批");
            } else if (BmsConstant.VERIFY_OPINION_REJECT.equals(option)) {  //若审批意见为驳回，则设审批状态为拒绝
                applyEntity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                entity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                wxService.sendWeixinTemplateMsg(applyCreator, "尊敬的用户，您的【访客申请】进度更新了",
                        "审批已驳回", "pages/visit/apply/applyList", "已驳回");
            } else {
                return new Result().error("审批意见异常");
            }
        } else if (BmsConstant.VISIT_SEC_LEVEL.equals(level)) {  // 二级审批
            if (BmsConstant.VERIFY_OPINION_APPROVE.equals(option)) {  //若审批意见为同意，则设审批状态为同意
                applyEntity.setStatus(BmsConstant.VERIFY_STATUS_APPROVE);
                entity.setStatus(BmsConstant.VERIFY_STATUS_APPROVE);
                wxService.sendWeixinTemplateMsg(applyCreator, "尊敬的用户，您的【访客申请】进度更新了",
                        "审批已通过", "pages/visit/apply/applyList", "已通过");
                // 通知保安
                List<SysUserDTO> userList = userDao.selectUserByPermission(BmsConstant.PERMISSION_VISIT_NOTICE);
                for (SysUserDTO user : userList) {
                    wxService.sendWeixinTemplateMsg(user.getId(), "尊敬的用户，您的【访客报备】记录更新了",
                            applyDTO.getRealName() + "向您报备了新的访客，请到平台查看记录",
                            "pages/visit/applyed/applyedList","待来访");
                }
            } else if (BmsConstant.VERIFY_OPINION_REJECT.equals(option)) {  //若审批意见为驳回，则设审批状态为拒绝
                applyEntity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                entity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                wxService.sendWeixinTemplateMsg(applyCreator, "尊敬的用户，您的【访客申请】进度更新了",
                        "审批已驳回", "pages/visit/apply/applyList", "已驳回");
            }
        }

        applyEntity.setId(dto.getApplyId());
        //applyEntity.setVerifyUserId(nextUserId);
        applyEntity.setVerifyUserId(addressUserDTO.getUserId());
        applyDao.updateById(applyEntity);

        entity.setApplyId(dto.getApplyId());
        //entity.setNextUserId(nextUserId);
        if (BmsConstant.VISIT_FIR_LEVEL.equals(level) && BmsConstant.VERIFY_OPINION_APPROVE.equals(option)) {
            entity.setNextUserId(addressUserDTO.getUserId());
        } else {
            entity.setNextUserId(null);
        }
        entity.setLevel(level);
        entity.setReplyContent(dto.getReplyContent());
        baseDao.insert(entity);

        return new Result();
    }
}