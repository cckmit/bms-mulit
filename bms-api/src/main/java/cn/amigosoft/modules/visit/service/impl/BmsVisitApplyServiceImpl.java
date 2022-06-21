package cn.amigosoft.modules.visit.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.service.SysUserService;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyAddressDao;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyDao;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyVerifyDao;
import cn.amigosoft.modules.visit.dao.BmsVisitApplyVisitorDao;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyAddressEntity;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVerifyEntity;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.visit.service.BmsVisitApplyService;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表 
 */
@Service
public class BmsVisitApplyServiceImpl extends CrudServiceImpl<BmsVisitApplyDao, BmsVisitApplyEntity, BmsVisitApplyDTO> implements BmsVisitApplyService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsVisitApplyVisitorDao visitorDao;

    @Autowired
    private BmsVisitApplyAddressDao applyAddressDao;

    @Autowired
    private BmsVisitApplyVerifyDao applyVerifyDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserService userService;

    @Override
    public QueryWrapper<BmsVisitApplyEntity> getWrapper(Map<String, Object> params){

        QueryWrapper<BmsVisitApplyEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    /**
     * 申请人获取报备记录
     */
    @Override
    public PageData<BmsVisitApplyDTO> queryPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("userId", creator);
        IPage<BmsVisitApplyEntity> page = getPage(params, "a.create_date", false);
        List<BmsVisitApplyDTO> list = baseDao.queryPage(params);
        return getPageData(list, page.getTotal(), BmsVisitApplyDTO.class);

    }

    /**
     * 申请人获取草稿记录
     * */
    @Override
    public PageData<BmsVisitApplyDTO> queryDraftPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        IPage<BmsVisitApplyEntity> page = getPage(params, "a.create_date", false);
        List<BmsVisitApplyDTO> list = baseDao.queryDraftPage(params);
        return getPageData(list, page.getTotal(), BmsVisitApplyDTO.class);
    }

    /**
     * 审批人获取报备记录
     */
    @Override
    public PageData<BmsVisitApplyDTO> queryVerifyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("verifyUserId", creator);
        IPage<BmsVisitApplyEntity> page = getPage(params, "a.create_date", false);
        List<BmsVisitApplyDTO> list = baseDao.queryVerifyPage(params);
        return getPageData(list, page.getTotal(), BmsVisitApplyDTO.class);

    }

    /**
     * 安保人员获取报备记录
     */
    @Override
    public PageData<BmsVisitApplyDTO> queryAppliedPage(Map<String, Object> params) {
        IPage<BmsVisitApplyEntity> page = getPage(params, "a.update_date", false);
        List<BmsVisitApplyDTO> list = baseDao.queryAppliedPage(params);
        return getPageData(list, page.getTotal(), BmsVisitApplyDTO.class);
    }


    /**
     * 获取访客报备申请详情
     */
    @Override
    public BmsVisitApplyDTO getDetail(Long id) {
        BmsVisitApplyDTO visitApplyDTO = baseDao.getDetail(id);
        // 访客列表
        List<BmsVisitApplyVisitorDTO> visitorDTOS = visitorDao.getVisitorList(id);
        visitApplyDTO.setVisitorDTOList(visitorDTOS);
        // 审批流程
        List<BmsVisitApplyVerifyDTO> verifyList = applyVerifyDao.selectVerifyList(id);
        visitApplyDTO.setVerifyList(verifyList);
        // 记录当前用户ID
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        visitApplyDTO.setCurrentUserId(userId);
        return visitApplyDTO;
    }

    /**
     * 获取访客报备申请详情
     */
    @Override
    public BmsVisitApplyDTO getDraftDetail(Long id) {
        BmsVisitApplyDTO visitApplyDTO = baseDao.getDetailNew(id);
        // 访客列表
        List<BmsVisitApplyVisitorDTO> visitorDTOS = visitorDao.getVisitorList(id);
        visitApplyDTO.setVisitorDTOList(visitorDTOS);
        // 审批流程
        List<BmsVisitApplyVerifyDTO> verifyList = applyVerifyDao.selectVerifyList(id);
        visitApplyDTO.setVerifyList(verifyList);
        // 记录当前用户ID
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        visitApplyDTO.setCurrentUserId(userId);
        return visitApplyDTO;
    }

    /**
     * 新增访客报备申请
     */
    @Override
    public Result addVisitApply(BmsVisitApplyDTO dto) {
        Result result = new Result();
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        dto.setUserId(userId);  //被访人ID
        dto.setStatus(BmsConstant.VERIFY_STATUS_PENDING);     //初始状态为待审核
        // 新增访客报备记录
        super.save(dto);
        // 获取访客列表
        List<BmsVisitApplyVisitorDTO> visitorDTOList = dto.getVisitorDTOList();
        // 遍历，新增访客
        for (BmsVisitApplyVisitorDTO visitorDTO : visitorDTOList) {
            BmsVisitApplyVisitorEntity visitorEntity = new BmsVisitApplyVisitorEntity();
            visitorEntity.setApplyId(dto.getId());
            visitorEntity.setName(visitorDTO.getName());
            visitorEntity.setIdCard(visitorDTO.getIdCard());
            visitorEntity.setMobile(visitorDTO.getMobile());
            visitorEntity.setCompany(visitorDTO.getCompany());
            visitorEntity.setSourceProvince(visitorDTO.getSourceProvince());
            visitorEntity.setSourceCity(visitorDTO.getSourceCity());
            visitorEntity.setImgs(StringUtils.join(visitorDTO.getImgList(), ";"));
            visitorEntity.setCreator(userId);
            visitorEntity.setCreateDate(new Date());
            visitorDao.insert(visitorEntity);
        }
        /*// 获取访问地区列表
        List<Long> addressIds = dto.getAddressIds();
        // 访问地点多选：遍历，新增申请表与访问地点表的关联关系
        for (Long addressId : addressIds) {
            BmsVisitApplyAddressEntity applyAddressEntity = new BmsVisitApplyAddressEntity();
            applyAddressEntity.setApplyId(dto.getId());
            applyAddressEntity.setAddressId(addressId);
            applyAddressEntity.setCreator(userId);
            applyAddressEntity.setCreateDate(new Date());
            applyAddressDao.insert(applyAddressEntity);
        }*/
        // 访问地点单选：直接新增申请表与访问地点表的关联关系
        BmsVisitApplyAddressEntity applyAddressEntity = new BmsVisitApplyAddressEntity();
        applyAddressEntity.setApplyId(dto.getId());
        applyAddressEntity.setAddressId(dto.getAddressId());
        applyAddressEntity.setCreator(userId);
        applyAddressEntity.setCreateDate(new Date());
        applyAddressDao.insert(applyAddressEntity);
        // 级别：提交申请
        BmsVisitApplyVerifyEntity visitApplyVerifyEntity = new BmsVisitApplyVerifyEntity();
        visitApplyVerifyEntity.setApplyId(dto.getId());
        visitApplyVerifyEntity.setNextUserId(dto.getVerifyUserId());
        visitApplyVerifyEntity.setCreator(userId);
        visitApplyVerifyEntity.setCreateDate(new Date());
        visitApplyVerifyEntity.setLevel(0);
        applyVerifyDao.insert(visitApplyVerifyEntity);
        // 向审批人员发送通知
        wxService.sendWeixinTemplateMsg(dto.getVerifyUserId(), "尊敬的用户，您有新的【访客审批】待处理", user.getRealName() + "向您提交了访客申请，请到平台进行审批","pages/visit/verify/verifyList","待审批");

        return result;
    }

    /**
     * 新增访客报备登记
     */
    @Override
    public Result addVisitRegister(BmsVisitApplyDTO dto) {
        Result result = new Result();
        // 被访人用传入的userId
        Long userId = dto.getUserId();
        // 状态为待提交（值为3）
        dto.setStatus(BmsConstant.VERIFY_STATUS_VISIT);
        //dto.setCreator(null);
        // 新增访客登记记录
        super.save(dto);
        // 获取访客列表
        List<BmsVisitApplyVisitorDTO> visitorList = dto.getVisitorDTOList();
        // 遍历，新增访客
        for (BmsVisitApplyVisitorDTO visitor : visitorList) {
            BmsVisitApplyVisitorEntity visitorEntity = new BmsVisitApplyVisitorEntity();
            visitorEntity.setApplyId(dto.getId());
            visitorEntity.setName(visitor.getName());
            visitorEntity.setIdCard(visitor.getIdCard());
            visitorEntity.setMobile(visitor.getMobile());
            visitorEntity.setCompany(visitor.getCompany());
            visitorEntity.setSourceProvince(visitor.getSourceProvince());
            visitorEntity.setSourceCity(visitor.getSourceCity());
            visitorEntity.setImgs(StringUtils.join(visitor.getImgList(), ";"));
            //visitorEntity.setCreator(null);
            //visitorEntity.setCreateDate(new Date());
            visitorDao.insert(visitorEntity);
        }
        // 访问地点单选：直接新增申请表与访问地点表的关联关系
        BmsVisitApplyAddressEntity applyAddressEntity = new BmsVisitApplyAddressEntity();
        applyAddressEntity.setApplyId(dto.getId());
        applyAddressEntity.setAddressId(dto.getAddressId());
        //applyAddressEntity.setCreator(null);
        //applyAddressEntity.setCreateDate(new Date());
        applyAddressDao.insert(applyAddressEntity);
        // 向被访人发送通知
        wxService.sendWeixinTemplateMsg(userId, "尊敬的用户，您有新的【访客报备】待提交", "有访客向您提交了来访申请，请到平台进行查看","pages/visit/apply/applyList","待提交");

        return result;
    }

    /**
     * 提交访客报备登记
     * */
    @Override
    public Result approveRegister(BmsVisitApplyDTO dto) {
        Result result = new Result();
        UserDetail user = SecurityUser.getUser();
        BmsVisitApplyEntity entity = ConvertUtils.sourceToTarget(dto, BmsVisitApplyEntity.class);
        entity.setStatus(BmsConstant.VERIFY_STATUS_PENDING);
        // 更新
        baseDao.updateById(entity);
        // 级别：提交申请
        BmsVisitApplyVerifyEntity verifyEntity = new BmsVisitApplyVerifyEntity();
        verifyEntity.setApplyId(dto.getId());
        verifyEntity.setNextUserId(dto.getVerifyUserId());
        verifyEntity.setLevel(0);
        applyVerifyDao.insert(verifyEntity);
        // 向审批人员发送通知
        wxService.sendWeixinTemplateMsg(dto.getVerifyUserId(), "尊敬的用户，您有新的【访客审批】待处理", user.getRealName() + "向您提交了访客申请，请到平台进行审批","pages/visit/verify/verifyList","待审批");

        return result;
    }

    /**
     * 删除访客报备登记
     * */
    @Override
    public Result deleteVisitRegister(Long id) {
        Result result = new Result();
        BmsVisitApplyEntity entity = new BmsVisitApplyEntity();
        entity.setId(id);
        entity.setDel(BmsConstant.DEL);
        baseDao.updateById(entity);
        /* 删除关联访客 */
        LambdaUpdateWrapper<BmsVisitApplyVisitorEntity> visitorWrapper = new LambdaUpdateWrapper<>();
        visitorWrapper.set(BmsVisitApplyVisitorEntity::getDel, BmsConstant.DEL);
        visitorWrapper.eq(BmsVisitApplyVisitorEntity::getApplyId, id);
        visitorDao.update(null, visitorWrapper);
        /* 删除关联地点 */
        LambdaUpdateWrapper<BmsVisitApplyAddressEntity> addressWrapper = new LambdaUpdateWrapper<>();
        addressWrapper.set(BmsVisitApplyAddressEntity::getDel, BmsConstant.DEL);
        addressWrapper.eq(BmsVisitApplyAddressEntity::getApplyId, id);
        applyAddressDao.update(null, addressWrapper);
        return result;
    }

    /**
     * 新增访客报备草稿
     * */
    @Override
    public Result addVisitDraft(BmsVisitApplyDTO dto) {
        Result result = new Result();
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        dto.setUserId(userId);  // 被访人ID
        dto.setStatus(BmsConstant.VERIFY_STATUS_DRAFT);   // 草稿状态
        // 新增访客草稿记录
        super.save(dto);
        // 获取访客列表
        List<BmsVisitApplyVisitorDTO> visitorDTOList = dto.getVisitorDTOList();
        // 遍历，新增访客
        for (BmsVisitApplyVisitorDTO visitorDTO : visitorDTOList) {
            BmsVisitApplyVisitorEntity visitorEntity = new BmsVisitApplyVisitorEntity();
            visitorEntity.setApplyId(dto.getId());
            visitorEntity.setName(visitorDTO.getName());
            visitorEntity.setIdCard(visitorDTO.getIdCard());
            visitorEntity.setMobile(visitorDTO.getMobile());
            visitorEntity.setCompany(visitorDTO.getCompany());
            visitorEntity.setSourceProvince(visitorDTO.getSourceProvince());
            visitorEntity.setSourceCity(visitorDTO.getSourceCity());
            visitorEntity.setImgs(StringUtils.join(visitorDTO.getImgList(), ";"));
            visitorEntity.setCreator(userId);
            visitorEntity.setCreateDate(new Date());
            visitorDao.insert(visitorEntity);
        }
        // 访问地点单选：直接新增申请表与访问地点表的关联关系
        BmsVisitApplyAddressEntity applyAddressEntity = new BmsVisitApplyAddressEntity();
        applyAddressEntity.setApplyId(dto.getId());
        applyAddressEntity.setAddressId(dto.getAddressId());
        applyAddressEntity.setCreator(userId);
        applyAddressEntity.setCreateDate(new Date());
        applyAddressDao.insert(applyAddressEntity);
        return result;
    }

    @Override
    public Result deleteVisitDraft(Long id) {
        Result result = new Result();
        BmsVisitApplyEntity entity = new BmsVisitApplyEntity();
        entity.setId(id);
        entity.setDel(BmsConstant.DEL);
        baseDao.deleteById(id);
        /* 删除关联访客 */
        QueryWrapper<BmsVisitApplyVisitorEntity> visitorWrapper = new QueryWrapper<>();
        visitorWrapper.eq("apply_id", id);
        visitorDao.delete(visitorWrapper);
        /* 删除关联地点 */
        QueryWrapper<BmsVisitApplyAddressEntity> addressWrapper = new QueryWrapper<>();
        addressWrapper.eq("apply_id", id);
        applyAddressDao.delete(addressWrapper);
        return result;
    }

    /**
     * 获取主管审核人员列表
     */
    @Override
    public List<SysUserDTO> getVerifyList() {
        List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_VISIT_VERIFY);
        List<SysUserDTO> resultList = userService.filterDeptUser(list);
        return resultList;
    }

    /**
     * 获取保安审核人员列表
     */
    @Override
    public List<SysUserDTO> getGuardVerifyList() {
        List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_VISIT_GUARD_VERIFY);
        return list;
    }
}