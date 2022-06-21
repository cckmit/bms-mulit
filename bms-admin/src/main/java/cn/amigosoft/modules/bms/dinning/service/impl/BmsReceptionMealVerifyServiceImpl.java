package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsReceptionMealDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsReceptionMealVerifyDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVerifyEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsReceptionMealVerifyService;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxUserinfoDao;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxUserinfoEntity;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 接待餐审批表
 */
@Service
public class BmsReceptionMealVerifyServiceImpl extends CrudServiceImpl<BmsReceptionMealVerifyDao, BmsReceptionMealVerifyEntity, BmsReceptionMealVerifyDTO> implements BmsReceptionMealVerifyService {

    @Autowired
    private BmsReceptionMealDao receptionMealDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private BmsWxUserinfoDao userinfoDao;

    @Override
    public QueryWrapper<BmsReceptionMealVerifyEntity> getWrapper(Map<String, Object> params) {

        QueryWrapper<BmsReceptionMealVerifyEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }

    @Override
    public Result insertReceptionMealVerify(BmsReceptionMealVerifyDTO dto) {
        Long receptionMealId = dto.getReceptionMealId();
        BmsReceptionMealDTO data = receptionMealDao.selectReceptionDetail(receptionMealId);
        if (!BmsConstant.VISIT_STATUS_WAIT.equals(data.getStatus())) {
            return new Result().error("该接待餐状态已改变");
        }
        Integer level = dto.getLevel();
        Integer opinion = dto.getOpinion();
        Long nextUserId = dto.getNextUserId();
        Long creator = data.getCreator();
        if (level == 1) {
            if (opinion == 0) {
                wxService.sendWeixinTemplateMsg(nextUserId, "尊敬的用户，您有新的【接待餐审批】待处理", data.getApplyUserName() + "向您提交了接待餐申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
            } else if (opinion == 1) {
                BmsReceptionMealEntity receptionMealEntity = new BmsReceptionMealEntity();
                receptionMealEntity.setId(receptionMealId);
                receptionMealEntity.setStatus(BmsConstant.VISIT_STATUS_REJECT);
                receptionMealDao.updateById(receptionMealEntity);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新", "审批已驳回", "pages/dining/reception/receptionList", "已驳回");
            } else {
                return new Result().error("审批意见异常");
            }
        } else if (level == 2) {
            BmsReceptionMealEntity receptionMealEntity = new BmsReceptionMealEntity();
            receptionMealEntity.setId(receptionMealId);
            if (opinion == 0) {
                receptionMealEntity.setStatus(BmsConstant.VISIT_STATUS_APPROVE);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新", "审批已通过", "pages/dining/reception/receptionList", "已通过");
                List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_RECEPTION_MEAL_GRANT);
                for (SysUserDTO userDTO : list) {
                    wxService.sendWeixinTemplateMsg(userDTO.getId(), "尊敬的用户，您有新的【餐票发放】待处理", data.getApplyUserName()+"的接待餐申请审批已通过，可向其发放餐票", "pages/dining/reception/receptionCheckList", "待发放");
                }
            } else if (opinion == 1) {
                receptionMealEntity.setStatus(BmsConstant.VISIT_STATUS_REJECT);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新", "审批已驳回", "pages/dining/reception/receptionList", "已驳回");
            } else {
                return new Result().error("审批意见异常");
            }
            receptionMealDao.updateById(receptionMealEntity);
        }

        BmsReceptionMealVerifyEntity receptionMealVerifyEntity = new BmsReceptionMealVerifyEntity();
        receptionMealVerifyEntity.setLevel(level);
        receptionMealVerifyEntity.setNextUserId(nextUserId);
        receptionMealVerifyEntity.setOpinion(opinion);
        receptionMealVerifyEntity.setReplyContent(dto.getReplyContent());
        receptionMealVerifyEntity.setReceptionMealId(receptionMealId);
        baseDao.insert(receptionMealVerifyEntity);
        return new Result();
    }

}