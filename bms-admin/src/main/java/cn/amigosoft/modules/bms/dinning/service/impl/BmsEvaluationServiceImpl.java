package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsEvaluationDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsEvaluationUserDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationUserEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsEvaluationService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.sys.dao.SysDeptDao;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 评价表
 */
@Service
public class BmsEvaluationServiceImpl extends CrudServiceImpl<BmsEvaluationDao, BmsEvaluationEntity, BmsEvaluationDTO> implements BmsEvaluationService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysDeptDao deptDao;

    @Autowired
    private BmsEvaluationUserDao evaluationUserDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsEvaluationEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String title = (String) params.get("title");

        QueryWrapper<BmsEvaluationEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(id), "id", id);
        wrapper.like(StringUtils.isNotBlank(title), "title", title);

        return wrapper;
    }

    @Override
    public void save(BmsEvaluationDTO dto) {
        super.save(dto);
        Set<String> userIdSet = getUserIdSet(dto);
        Long id = dto.getId();
        insertEvaluationUser(id, userIdSet, dto.getTitle());
    }

    private Set<String> getUserIdSet(BmsEvaluationDTO dto) {
        String userIdList = dto.getUserIdList();
        Set<String> userIdSet = new HashSet<>();
        if (StringUtils.isNotEmpty(userIdList)) {
            String[] userIds = userIdList.split(",");
            userIdSet.addAll(Arrays.asList(userIds));
        }
        String deptIdList = dto.getDeptIdList();
        if (StringUtils.isNotEmpty(deptIdList)) {
            String[] deptIds = deptIdList.split(",");
            for (String deptId : deptIds) {
                List<String> userIds = userDao.selectUserIdsByDeptId(deptId);
                userIdSet.addAll(userIds);
            }
        }
        return userIdSet;
    }

    @Override
    public void update(BmsEvaluationDTO dto) {
        Long id = dto.getId();
        Date endDate = dto.getEndDate();
        BmsEvaluationEntity entity = new BmsEvaluationEntity();
        entity.setId(id);
        entity.setEndDate(endDate);
        baseDao.updateById(entity);
    }

    private void insertEvaluationUser(Long evaluationId, Set<String> userIdSet, String title) {
        for (String userId : userIdSet) {
            BmsEvaluationUserEntity evaluationUser = new BmsEvaluationUserEntity();
            evaluationUser.setEvaluationId(evaluationId);
            evaluationUser.setUserId(Long.parseLong(userId));
            evaluationUserDao.insert(evaluationUser);
            wxService.sendWeixinTemplateMsg(Long.parseLong(userId), "尊敬的用户，您有新的【用餐评价】待处理", title + "，请到平台完成评价", "pages/evaluation/evaluationList", "待评价");
        }
    }

    @Override
    public Result<PageData<BmsEvaluationDTO>> getPage(Map<String, Object> params) {
        IPage<BmsEvaluationEntity> page = getPage(params, "e.create_date", false);
        List<BmsEvaluationDTO> list = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsEvaluationDTO>>().ok(getPageData(list, page.getTotal(), BmsEvaluationDTO.class));
    }

    @Override
    public BmsEvaluationDTO selectEvaluationById(Long id) {
        BmsEvaluationDTO dto = baseDao.selectEvaluationById(id);
        String userIdList = dto.getUserIdList();
        if (StringUtils.isNotEmpty(userIdList)) {
            String[] userIds = userIdList.split(",");
            if (userIds.length > 0) {
                List<Map<String, String>> users = new ArrayList<>();
                List<SysUserDTO> list = userDao.selectBaseUserInfoList(userIds);
                for (SysUserDTO userDTO : list) {
                    Map<String, String> map = new HashMap<>(userIds.length);
                    map.put("key", String.valueOf(userDTO.getId()));
                    map.put("label", userDTO.getRealName() + "(" + userDTO.getWorkNo() + ")");
                    users.add(map);
                }
                dto.setUserList(users);
            }
        }
        String deptIdList = dto.getDeptIdList();
        if (StringUtils.isNotEmpty(deptIdList)) {
            String[] deptIds = deptIdList.split(",");
            if (deptIds.length > 0) {
                List<SysDeptDTO> list = deptDao.selectBaseDeptInfoList(deptIds);
                dto.setDeptList(list);
            }
        }
        return dto;
    }

    @Override
    public List<BmsEvaluationDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public void deleteEvaluationByIds(Long[] ids) {
        baseDao.deleteEvaluationByIds(ids);
        evaluationUserDao.deleteByEvaluationIds(ids);
    }

}