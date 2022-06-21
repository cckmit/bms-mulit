package cn.amigosoft.modules.bms.visit.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyAddressDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyVerifyDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitApplyVisitorDao;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyAddressEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVerifyEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.bms.visit.excel.BmsVisitApplyVisitorExcel;
import cn.amigosoft.modules.bms.visit.service.BmsVisitApplyService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表
 */
@Service
public class BmsVisitApplyServiceImpl extends CrudServiceImpl<BmsVisitApplyDao, BmsVisitApplyEntity, BmsVisitApplyDTO> implements BmsVisitApplyService {

    @Autowired
    private BmsVisitApplyVisitorDao visitApplyVisitorDao;

    @Autowired
    private BmsVisitApplyAddressDao visitApplyAddressDao;

    @Autowired
    private BmsVisitApplyVerifyDao visitApplyVerifyDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsVisitApplyEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsVisitApplyEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public boolean insert(BmsVisitApplyEntity entity) {
        return super.insert(entity);
    }

    @Override
    public Result<PageData<BmsVisitApplyDTO>> getApplyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        IPage<BmsVisitApplyEntity> page = getPage(params, "ap.create_date", false);
        List<BmsVisitApplyDTO> resultList = baseDao.selectPageList(page, params);
        completeApplyInfo(resultList);
        return new Result<PageData<BmsVisitApplyDTO>>().ok(getPageData(resultList, page.getTotal(), BmsVisitApplyDTO.class));
    }

    @Override
    public Result<PageData<BmsVisitApplyDTO>> getPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("verifyUserId", userId);
        IPage<BmsVisitApplyEntity> page = getPage(params, "ap.create_date", false);
        List<BmsVisitApplyDTO> resultList = baseDao.selectPageList(page, params);
        completeApplyInfo(resultList);
        return new Result<PageData<BmsVisitApplyDTO>>().ok(getPageData(resultList, page.getTotal(), BmsVisitApplyDTO.class));
    }

    @Override
    public Result<PageData<BmsVisitApplyDTO>> getApprovePage(Map<String, Object> params) {
        params.put(BmsConstant.STATUS_COLUMN_NAME, BmsConstant.VISIT_STATUS_APPROVE);
        IPage<BmsVisitApplyEntity> page = getPage(params, "ap.create_date", false);
        List<BmsVisitApplyDTO> resultList = baseDao.selectPageList(page, params);
        completeApplyInfo(resultList);
        return new Result<PageData<BmsVisitApplyDTO>>().ok(getPageData(resultList, page.getTotal(), BmsVisitApplyDTO.class));
    }

    private void completeApplyInfo(List<BmsVisitApplyDTO> list) {
        for (BmsVisitApplyDTO applyDTO : list) {
            Long id = applyDTO.getId();
            String addressName = baseDao.selectAddressNameByApplyId(id);
            applyDTO.setAddressName(addressName);
            String visitorName = visitApplyVisitorDao.selectVisitorNameByApplyId(id);
            applyDTO.setVisitorName(visitorName);
        }
    }

    @Override
    public BmsVisitApplyDTO selectVisitApplyById(Long id) {
        BmsVisitApplyDTO visitApplyDTO = baseDao.selectVisitApplyById(id);
        String addressName = baseDao.selectAddressNameByApplyId(id);
        visitApplyDTO.setAddressName(addressName);
        QueryWrapper<BmsVisitApplyVisitorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_id", id);
        queryWrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsVisitApplyVisitorEntity> visitorList = visitApplyVisitorDao.selectList(queryWrapper);
        List<BmsVisitApplyVisitorDTO> list = ConvertUtils.sourceToTarget(visitorList, BmsVisitApplyVisitorDTO.class);
        visitApplyDTO.setVisitorList(list);
        List<BmsVisitApplyVerifyDTO> verifyEntityList = visitApplyVerifyDao.selectVerifyListByApplyId(id);
        visitApplyDTO.setVerifyList(verifyEntityList);
        return visitApplyDTO;
    }

    @Override
    public Result insertVisitApply(BmsVisitApplyDTO dto) {
        List<BmsVisitApplyVisitorDTO> visitorList = dto.getVisitorList();
        if (visitorList == null || visitorList.size() == 0) {
            return new Result().error("请输入访客信息");
        }
        List<BmsVisitAddressDTO> visitAddressList = dto.getVisitAddressList();
        if (visitAddressList == null || visitAddressList.size() == 0) {
            return new Result().error("请输入访问地点信息");
        }
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        BmsVisitApplyEntity entity = new BmsVisitApplyEntity();
        Long verifyUserId = dto.getVerifyUserId();
        entity.setUserId(userId);
        entity.setBeginDate(dto.getBeginDate());
        entity.setEndDate(dto.getEndDate());
        entity.setMatter(dto.getMatter());
        entity.setStatus(BmsConstant.VISIT_STATUS_WAIT);
        baseDao.insert(entity);

        Long id = entity.getId();
        BmsVisitApplyVerifyEntity verifyEntity = new BmsVisitApplyVerifyEntity();
        verifyEntity.setApplyId(id);
        verifyEntity.setLevel(0);
        verifyEntity.setNextUserId(verifyUserId);
        visitApplyVerifyDao.insert(verifyEntity);

        for (BmsVisitAddressDTO address : visitAddressList) {
            BmsVisitApplyAddressEntity addressEntity = new BmsVisitApplyAddressEntity();
            addressEntity.setApplyId(id);
            addressEntity.setAddressId(address.getId());
            visitApplyAddressDao.insert(addressEntity);
        }

        for (BmsVisitApplyVisitorDTO visitor : visitorList) {
            BmsVisitApplyVisitorEntity visitorEntity = new BmsVisitApplyVisitorEntity();
            visitorEntity.setApplyId(id);
            visitorEntity.setCompany(visitor.getCompany());
            visitorEntity.setName(visitor.getName());
            visitorEntity.setMobile(visitor.getMobile());
            visitorEntity.setIdCard(visitor.getIdCard());
            visitorEntity.setSourceProvince(visitor.getSourceProvince());
            visitorEntity.setSourceCity(visitor.getSourceCity());
            visitorEntity.setImgs(visitor.getImgs());
            visitApplyVisitorDao.insert(visitorEntity);
        }

        wxService.sendWeixinTemplateMsg(verifyUserId, "尊敬的用户，您有新的【访客审批】待处理", user.getRealName() + "向您提交了访客申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
        return new Result();
    }

    @Override
    public List<BmsVisitApplyDTO> selectExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        List<BmsVisitApplyDTO> resultList = baseDao.selectExportList(params);
        for (BmsVisitApplyDTO applyDTO : resultList) {
            Long id = applyDTO.getId();
            String addressName = baseDao.selectAddressNameByApplyId(id);
            applyDTO.setAddressName(addressName);
            List<BmsVisitApplyVisitorExcel> visitorList = visitApplyVisitorDao.selectVisitorExcelDataByApplyId(id);
            applyDTO.setVisitors(visitorList);
        }
        return resultList;
    }

    @Override
    public List<BmsVisitApplyDTO> selectVerifyExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("verifyUserId", userId);
        List<BmsVisitApplyDTO> resultList = baseDao.selectExportList(params);
        for (BmsVisitApplyDTO applyDTO : resultList) {
            Long id = applyDTO.getId();
            String addressName = baseDao.selectAddressNameByApplyId(id);
            applyDTO.setAddressName(addressName);
            List<BmsVisitApplyVisitorExcel> visitorList = visitApplyVisitorDao.selectVisitorExcelDataByApplyId(id);
            applyDTO.setVisitors(visitorList);
        }
        return resultList;
    }

    @Override
    public List<BmsVisitApplyDTO> selectRecordExportList(Map<String, Object> params) {
        params.put(BmsConstant.STATUS_COLUMN_NAME, BmsConstant.VISIT_STATUS_APPROVE);
        List<BmsVisitApplyDTO> resultList = baseDao.selectExportList(params);
        for (BmsVisitApplyDTO applyDTO : resultList) {
            Long id = applyDTO.getId();
            String addressName = baseDao.selectAddressNameByApplyId(id);
            applyDTO.setAddressName(addressName);
            List<BmsVisitApplyVisitorExcel> visitorList = visitApplyVisitorDao.selectVisitorExcelDataByApplyId(id);
            applyDTO.setVisitors(visitorList);
        }
        return resultList;
    }

}