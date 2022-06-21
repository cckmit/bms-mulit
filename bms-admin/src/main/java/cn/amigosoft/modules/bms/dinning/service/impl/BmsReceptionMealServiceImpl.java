package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsMealTypeDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsReceptionMealDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsReceptionMealVerifyDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsReceptionMealVisitorDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVisitorDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVerifyEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVisitorEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsReceptionMealVisitorExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsReceptionMealService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 接待餐表
 */
@Service
public class BmsReceptionMealServiceImpl extends CrudServiceImpl<BmsReceptionMealDao, BmsReceptionMealEntity, BmsReceptionMealDTO> implements BmsReceptionMealService {

    @Autowired
    private BmsReceptionMealVisitorDao receptionMealVisitorDao;

    @Autowired
    private BmsMealTypeDao mealTypeDao;

    @Autowired
    private BmsReceptionMealVerifyDao receptionMealVerifyDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsReceptionMealEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsReceptionMealEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result<PageData<BmsReceptionMealDTO>> getPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        IPage<BmsReceptionMealEntity> page = getPage(params, "r.create_date", false);
        List<BmsReceptionMealDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsReceptionMealDTO>>().ok(getPageData(resultList, page.getTotal(), BmsReceptionMealDTO.class));
    }

    @Override
    public Result<PageData<BmsReceptionMealDTO>> getVerifyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("verifyId", userId);
        IPage<BmsReceptionMealEntity> page = getPage(params, "r.create_date", false);
        List<BmsReceptionMealDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsReceptionMealDTO>>().ok(getPageData(resultList, page.getTotal(), BmsReceptionMealDTO.class));
    }

    @Override
    public Result<PageData<BmsReceptionMealDTO>> getStatisticsPage(Map<String, Object> params) {
        IPage<BmsReceptionMealEntity> page = getPage(params, "r.create_date", false);
        List<BmsReceptionMealDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsReceptionMealDTO>>().ok(getPageData(resultList, page.getTotal(), BmsReceptionMealDTO.class));
    }

    @Override
    public BmsReceptionMealDTO selectReceptionDetail(Long id) {
        BmsReceptionMealDTO dto = baseDao.selectReceptionDetail(id);
        List<BmsReceptionMealVisitorDTO> list = receptionMealVisitorDao.selectByReceptionMealId(id);
        dto.setVisitorList(list);
        List<BmsReceptionMealVerifyDTO> verifyList = receptionMealVerifyDao.selectVerifyListByReceptionMealId(id);
        dto.setVerifyList(verifyList);
        return dto;
    }

    @Override
    public Result saveReceptionMeal(BmsReceptionMealDTO dto) {
        String eatDate = dto.getEatDate();
        Long mealTypeId = dto.getMealTypeId();
        BmsMealTypeEntity mealTypeEntity = mealTypeDao.selectById(mealTypeId);
        Integer advanceOrderTime = mealTypeEntity.getAdvanceOrderTime();
        String beginTime = mealTypeEntity.getBeginTime();
        Integer[] yyyyMMdd = split(eatDate, "-");
        Integer[] HHmm = split(beginTime, ":");
        // 可以提前订餐的最晚时间
        DateTime eatDateTime = new DateTime(yyyyMMdd[0], yyyyMMdd[1], yyyyMMdd[2], HHmm[0], HHmm[1], 0).minusHours(advanceOrderTime);
        if (new DateTime().isAfter(eatDateTime)) {
            return new Result().error("已超过可订餐时间");
        }
        super.save(dto);
        Long id = dto.getId();
        List<BmsReceptionMealVisitorDTO> visitorList = dto.getVisitorList();
        for (BmsReceptionMealVisitorDTO visitorDTO : visitorList) {
            BmsReceptionMealVisitorEntity entity = new BmsReceptionMealVisitorEntity();
            entity.setReceptionMealId(id);
            entity.setVisitorName(visitorDTO.getVisitorName());
            entity.setVisitorPhone(visitorDTO.getVisitorPhone());
            receptionMealVisitorDao.insert(entity);
        }
        BmsReceptionMealVerifyEntity verifyEntity = new BmsReceptionMealVerifyEntity();
        Long verifyId = dto.getVerifyId();
        verifyEntity.setReceptionMealId(id);
        verifyEntity.setLevel(0);
        verifyEntity.setNextUserId(verifyId);
        receptionMealVerifyDao.insert(verifyEntity);
        wxService.sendWeixinTemplateMsg(verifyId, "尊敬的用户，您有新的【接待餐审批】待处理", SecurityUser.getUser().getRealName() + "向您提交了接待餐申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
        return new Result();
    }

    @Override
    public List<BmsReceptionMealDTO> selectApplyExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("creator", userId);
        List<BmsReceptionMealDTO> list = baseDao.selectExportList(params);
        for (BmsReceptionMealDTO receptionMealDTO : list) {
            Long id = receptionMealDTO.getId();
            List<BmsReceptionMealVisitorExcel> visitors = receptionMealVisitorDao.selectExcelInfoByReceptionMealId(id);
            receptionMealDTO.setVisitors(visitors);
        }
        return list;
    }

    @Override
    public List<BmsReceptionMealDTO> selectVerifyExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("verifyId", userId);
        List<BmsReceptionMealDTO> list = baseDao.selectExportList(params);
        for (BmsReceptionMealDTO receptionMealDTO : list) {
            Long id = receptionMealDTO.getId();
            List<BmsReceptionMealVisitorExcel> visitors = receptionMealVisitorDao.selectExcelInfoByReceptionMealId(id);
            receptionMealDTO.setVisitors(visitors);
        }
        return list;
    }

    @Override
    public List<BmsReceptionMealDTO> selectStatisticsExport(Map<String, Object> params) {
        List<BmsReceptionMealDTO> list = baseDao.selectExportList(params);
        for (BmsReceptionMealDTO receptionMealDTO : list) {
            Long id = receptionMealDTO.getId();
            List<BmsReceptionMealVisitorExcel> visitors = receptionMealVisitorDao.selectExcelInfoByReceptionMealId(id);
            receptionMealDTO.setVisitors(visitors);
        }
        return list;
    }

    private Integer[] split(String str, String regex) {
        String[] arr = str.split(regex);
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    @Override
    public Result<PageData<BmsReceptionMealDTO>> getCountPage(Map<String, Object> params) {
        IPage<BmsReceptionMealEntity> page = getPage(params, "a.eatDate", false);
        List<BmsReceptionMealDTO> resultList = baseDao.selectCountPageList(page, params);
        return new Result<PageData<BmsReceptionMealDTO>>().ok(getPageData(resultList, page.getTotal(), BmsReceptionMealDTO.class));
    }

    @Override
    public List<BmsReceptionMealDTO> selectCountExport(Map<String, Object> params) {
        return baseDao.selectCountExportList(params);
    }

}