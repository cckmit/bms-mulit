package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dao.BmsReceptionMealDao;
import cn.amigosoft.modules.dining.dao.BmsReceptionMealVerifyDao;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVerifyEntity;
import cn.amigosoft.modules.dining.service.BmsReceptionMealVerifyService;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private SysUserDao userDao;

    @Override
    public QueryWrapper<BmsReceptionMealVerifyEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsReceptionMealVerifyEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }


    @Override
    public Result saveVerify(BmsReceptionMealVerifyDTO dto) {
        Integer level = dto.getLevel();
        Integer opinion = dto.getOpinion();
        Long receptionMealId = dto.getReceptionMealId();
        QueryWrapper<BmsReceptionMealVerifyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reception_meal_id", receptionMealId);
        queryWrapper.eq("del", 0);
        List<BmsReceptionMealVerifyEntity> verifyEntityList = baseDao.selectList(queryWrapper);
        if (verifyEntityList.size() == 0) {
            return new Result().error("审批异常");
        }
        BmsReceptionMealVerifyEntity lastVerifyData = verifyEntityList.get(verifyEntityList.size() - 1);
        Integer dbLevel = lastVerifyData.getLevel();
        if (level.equals(dbLevel)) {
            return new Result().error("请勿重复审批");
        }


        BmsReceptionMealDTO data = receptionMealDao.selectReceptionDetail(receptionMealId);
        Long nextUserId = dto.getNextUserId();
        Long creator = data.getCreator();

        BmsReceptionMealEntity receptionMealEntity = new BmsReceptionMealEntity();
        receptionMealEntity.setId(receptionMealId);
        // 若为一级审批
        if (BmsConstant.RECEPTION_FIR_LEVEL.equals(level)) {
            if (BmsConstant.VERIFY_OPINION_APPROVE.equals(opinion)) {  //若审批意见为同意
                wxService.sendWeixinTemplateMsg(nextUserId, "尊敬的用户，您有新的【接待餐审批】待处理", data.getApplyUserName() + "向您提交了接待餐申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
            } else if (BmsConstant.VERIFY_OPINION_REJECT.equals(opinion)) {   //若审批意见为驳回，则设审批状态为拒绝
                receptionMealEntity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新了", "审批已驳回", "pages/dining/reception/receptionList", "已驳回");
            } else {
                return new Result().error("审批意见异常");
            }
        } else if (BmsConstant.RECEPTION_SEC_LEVEL.equals(level)) {  //若为二级审批
            if (BmsConstant.VERIFY_OPINION_APPROVE.equals(opinion)) {   //若审批意见为同意，则设审批状态为同意
                receptionMealEntity.setStatus(BmsConstant.VERIFY_STATUS_APPROVE);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新了", "审批已通过", "pages/dining/reception/receptionList", "已通过");
                List<SysUserDTO> list = userDao.selectUserByPermission(BmsConstant.PERMISSION_RECEPTION_MEAL_GRANT);
                for (SysUserDTO userDTO : list) {
                    wxService.sendWeixinTemplateMsg(userDTO.getId(), "尊敬的用户，您有新的【餐票发放】待处理", data.getApplyUserName()+"的接待餐申请审批已通过，可向其发放餐票", "pages/dining/reception/receptionCheckList", "待发放");
                }
            } else if (BmsConstant.VERIFY_OPINION_REJECT.equals(opinion)) { //若审批意见为驳回，则设审批状态为拒绝
                receptionMealEntity.setStatus(BmsConstant.VERIFY_STATUS_REJECT);
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【接待餐申请】进度更新了", "审批已驳回", "pages/dining/reception/receptionList", "已驳回");
            } else {
                return new Result().error("审批意见异常");
            }
        }
        receptionMealDao.updateById(receptionMealEntity);

        BmsReceptionMealVerifyEntity verifyEntity = new BmsReceptionMealVerifyEntity();
        verifyEntity.setReceptionMealId(receptionMealId);
        verifyEntity.setOpinion(opinion);
        verifyEntity.setNextUserId(dto.getNextUserId());
        verifyEntity.setLevel(level);
        verifyEntity.setReplyContent(dto.getReplyContent());
        baseDao.insert(verifyEntity);
        return new Result();
    }
}