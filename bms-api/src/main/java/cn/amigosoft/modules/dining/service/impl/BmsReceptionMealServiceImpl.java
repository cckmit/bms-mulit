package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dao.BmsReceptionMealDao;
import cn.amigosoft.modules.dining.dao.BmsReceptionMealVerifyDao;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVisitorDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVerifyEntity;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVisitorEntity;
import cn.amigosoft.modules.dining.service.BmsReceptionMealService;
import cn.amigosoft.modules.dining.service.BmsReceptionMealVisitorService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.service.SysUserService;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接待餐表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Service
public class BmsReceptionMealServiceImpl extends CrudServiceImpl<BmsReceptionMealDao, BmsReceptionMealEntity, BmsReceptionMealDTO> implements BmsReceptionMealService {

    @Autowired
    private BmsReceptionMealVisitorService receptionMealVisitorService;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsReceptionMealVerifyDao receptionMealVerifyDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserService userService;

    @Override
    public QueryWrapper<BmsReceptionMealEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsReceptionMealEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    //接待餐分页查询
    @Override
    public PageData<BmsReceptionMealDTO> queryPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        IPage<BmsReceptionMealEntity> page = getPage(params, "a.eat_date", true);
        List<BmsReceptionMealDTO> list = baseDao.queryPage(params);

        return getPageData(list, page.getTotal(), BmsReceptionMealDTO.class);
    }

    @Override
    public PageData<BmsReceptionMealDTO> queryCheckPage(Map<String, Object> params) {
        IPage<BmsReceptionMealEntity> page = getPage(params, "a.create_date", false);
        List<BmsReceptionMealDTO> list = baseDao.queryCheckPage(params);

        return getPageData(list, page.getTotal(), BmsReceptionMealDTO.class);
    }

    //接待餐详情
    @Override
    public BmsReceptionMealDTO getDetail(Long id) {
        BmsReceptionMealDTO receptionMealDTO = baseDao.getDetail(id);
        List<BmsReceptionMealVisitorDTO> visitorDTOList = baseDao.getVisitors(id);
        receptionMealDTO.setVisitorDTOList(visitorDTOList);
        List<BmsReceptionMealVerifyDTO> verifyList = receptionMealVerifyDao.selectVerifyList(id);
        receptionMealDTO.setVerifyList(verifyList);
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        receptionMealDTO.setCurrentUserId(userId);
        return receptionMealDTO;
    }

    //新增接待餐
    @Override
    public Result insertReception(BmsReceptionMealDTO dto) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        Result result = new Result();
        // 新增接待餐申请记录
        super.save(dto);
        // 获取访客列表
        List<BmsReceptionMealVisitorDTO> visitorDTOList = dto.getVisitorDTOList();
        // 遍历，新增访客
        for (BmsReceptionMealVisitorDTO visitorDTO : visitorDTOList) {
            BmsReceptionMealVisitorEntity visitorEntity = new BmsReceptionMealVisitorEntity();
            visitorEntity.setReceptionMealId(dto.getId());
            visitorEntity.setVisitorName(visitorDTO.getVisitorName());
            visitorEntity.setVisitorPhone(visitorDTO.getVisitorPhone());
            visitorEntity.setCreator(userId);
            receptionMealVisitorService.insert(visitorEntity);
        }
        BmsReceptionMealVerifyEntity receptionMealVerifyEntity = new BmsReceptionMealVerifyEntity();
        receptionMealVerifyEntity.setReceptionMealId(dto.getId());
        receptionMealVerifyEntity.setNextUserId(dto.getVerifyId());
        receptionMealVerifyEntity.setCreator(userId);
        receptionMealVerifyEntity.setCreateDate(new Date());
        // 级别：提交申请
        receptionMealVerifyEntity.setLevel(BmsConstant.RECEPTION_PENDING);
        receptionMealVerifyDao.insert(receptionMealVerifyEntity);
        wxService.sendWeixinTemplateMsg(dto.getVerifyId(), "尊敬的用户，您有新的【接待餐审批】待处理", user.getRealName() + "向您提交了接待餐申请，请到平台进行审批", "pages/dining/reception/receptionVerifyList", "待审批");
        return result;
    }

    //删除接待餐
    @Override
    public Result deleteReception(Long id) {
        try {
            Result result = new Result();
            /*// 将用餐日期转成字符串
            BmsReceptionMealDTO dto = baseDao.getEatTime(id);
            String eatDate = dto.getEatDate();
            // 与用餐开始时间进行拼接
            String eatDateTime = eatDate.concat(" ").concat(dto.getBeginTime()).concat(":00");
            // 转成date类型
            DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginDateTime = dateTimeFormat.parse(eatDateTime);
            // 与当前时间做对比
            if (new Date().after(beginDateTime)) {
                return result.error("该接待餐申请已过用餐时段，无法删除");
            }*/
            BmsReceptionMealEntity entity = new BmsReceptionMealEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            baseDao.updateById(entity);
            baseDao.deleteVerifies(id);
            baseDao.deleteVisitors(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取食堂列表
    @Override
    public List<BmsReceptionMealDTO> getCanteenList() {
        return baseDao.getCanteenList();
    }

    //获取食堂列表
    @Override
    public List<BmsReceptionMealDTO> getMealTypeList() {
        return baseDao.getMealTypeList();
    }

    @Override
    public List<SysUserDTO> getManagerVerifyList() {
        List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_MANAGER_VERIFY);
        List<SysUserDTO> resultList = userService.filterDeptUser(list);
        return resultList;
    }

    @Override
    public List<SysUserDTO> getOfficeVerifyList() {
        List<SysUserDTO> list = userDao.selectUserListByPermission(BmsConstant.PERMISSION_OFFICE_VERIFY);
        return list;
    }

    @Override
    public PageData<BmsReceptionMealDTO> queryVerifyPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long nextUserId = user.getId();
        params.put("nextUserId", nextUserId);
        IPage<BmsReceptionMealEntity> page = getPage(params, "r.create_date", false);
        List<BmsReceptionMealDTO> list = baseDao.queryVerifyPage(page, params);
        return getPageData(list, page.getTotal(), BmsReceptionMealDTO.class);
    }

}