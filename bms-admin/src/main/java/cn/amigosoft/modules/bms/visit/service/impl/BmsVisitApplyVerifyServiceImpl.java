package cn.amigosoft.modules.bms.visit.service.impl;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitAddressUserDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyAddressDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyVerifyDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressUserEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyAddressEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVerifyEntity;
import cn.amigosoft.modules.bms.visit.service.BmsVisitApplyVerifyService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 人员报备审批表
 */
@Service
public class BmsVisitApplyVerifyServiceImpl extends CrudServiceImpl<BmsVisitApplyVerifyDao, BmsVisitApplyVerifyEntity, BmsVisitApplyVerifyDTO> implements BmsVisitApplyVerifyService {

    @Autowired
    private BmsVisitApplyDao visitApplyDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsVisitApplyAddressDao visitApplyAddressDao;

    @Autowired
    private BmsVisitAddressUserDao visitAddressUserDao;

    @Override
    public QueryWrapper<BmsVisitApplyVerifyEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsVisitApplyVerifyEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result insertVisitApplyVerify(BmsVisitApplyVerifyDTO dto) {
        Long applyId = dto.getApplyId();
        Integer status = dto.getStatus();
        if (BmsConstant.VISIT_STATUS_APPROVE.equals(status) || BmsConstant.VISIT_STATUS_REJECT.equals(status)) {
            BmsVisitApplyDTO data = visitApplyDao.selectVisitApplyById(applyId);
            if (!BmsConstant.VISIT_STATUS_WAIT.equals(data.getStatus())) {
                return new Result().error("该访客申请状态已改变");
            }
            Integer level = dto.getLevel();
            Long applyUserId = data.getCreator();
            if (level == 1) {
                if (BmsConstant.VISIT_STATUS_APPROVE.equals(status)) {
                    QueryWrapper<BmsVisitApplyAddressEntity> queryAddress = new QueryWrapper<>();
                    queryAddress.eq("apply_id", applyId);
                    List<BmsVisitApplyAddressEntity> addressList = visitApplyAddressDao.selectList(queryAddress);
                    if (addressList.size() == 0) {
                        return new Result().error("该申请未选择访问地点");
                    }
                    Long addressId = addressList.get(0).getAddressId();
                    QueryWrapper<BmsVisitAddressUserEntity> queryUser = new QueryWrapper<>();
                    queryUser.eq("address_id", addressId);
                    List<BmsVisitAddressUserEntity> userList = visitAddressUserDao.selectList(queryUser);
                    if (userList.size() == 0) {
                        return new Result().error("该申请所选择的访问地点未配置审核人员");
                    }
                    Long nextUserId = userList.get(0).getUserId();
                    dto.setNextUserId(nextUserId);
                    wxService.sendWeixinTemplateMsg(nextUserId, "尊敬的用户，您有新的【访客审批】待处理", data.getUserName() + "向您提交了访客申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
                } else {
                    wxService.sendWeixinTemplateMsg(applyUserId, "尊敬的用户，您的【访客申请】进度更新了", "审批已驳回", "pages/visit/apply/applyList", "已驳回");
                }
            } else {
                if (BmsConstant.VISIT_STATUS_APPROVE.equals(status)) {
                    wxService.sendWeixinTemplateMsg(applyUserId, "尊敬的用户，您的【访客申请】进度更新了", "审批已通过", "pages/visit/apply/applyList", "已通过");
                    List<SysUserDTO> userList = userDao.selectUserByPermission(BmsConstant.PERMISSION_VISIT_NOTICE);
                    for (SysUserDTO user : userList) {
                        wxService.sendWeixinTemplateMsg(user.getId(), "尊敬的用户，您的【访客报备】记录更新了", data.getUserName() + "向您报备了新的访客，请到平台查看记录", "pages/visit/applyed/applyedList", "待来访");
                    }
                } else {
                    wxService.sendWeixinTemplateMsg(applyUserId, "尊敬的用户，您的【访客申请】进度更新了", "审批已驳回", "pages/visit/apply/applyList", "已驳回");
                }
                BmsVisitApplyEntity applyEntity = new BmsVisitApplyEntity();
                applyEntity.setId(applyId);
                applyEntity.setStatus(status);
                visitApplyDao.updateById(applyEntity);
            }
            save(dto);
            return new Result();
        } else {
            return new Result().error("审批状态错误");
        }
    }
}
