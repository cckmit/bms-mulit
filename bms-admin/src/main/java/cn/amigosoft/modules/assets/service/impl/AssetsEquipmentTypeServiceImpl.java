package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.AssetsNoUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.TreeUtils;
import cn.amigosoft.modules.assets.dao.AssetsEquipmentTypeDao;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeDTO;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeTreeDTO;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentTypeEntity;
import cn.amigosoft.modules.assets.service.AssetsEquipmentService;
import cn.amigosoft.modules.assets.service.AssetsEquipmentTypeService;
import cn.amigosoft.modules.security.user.SecurityUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产设备分类表  服务实现类
 * <p>
 * ************************默认的3个顶级节点是10000全局性的 所以后面相关查询的sql需添加租户免隔离
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
@Service
public class AssetsEquipmentTypeServiceImpl extends CrudServiceImpl<AssetsEquipmentTypeDao, AssetsEquipmentTypeEntity, AssetsEquipmentTypeDTO> implements AssetsEquipmentTypeService {

    @Autowired
    private AssetsEquipmentService assetsEquipmentService;

    @Override
    public QueryWrapper<AssetsEquipmentTypeEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String tenantId = (String) params.get("tenantId");
        String pid = (String) params.get("pid");
        String pids = (String) params.get("pids");
        String typeNo = (String) params.get("typeNo");
        String name = (String) params.get("name");
        String value = (String) params.get("value");
        String remark = (String) params.get("remark");
        String creator = (String) params.get("creator");
        String createDate = (String) params.get("createDate");
        String updater = (String) params.get("updater");
        String updateDate = (String) params.get("updateDate");
        QueryWrapper<AssetsEquipmentTypeEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(tenantId), "tenant_id", tenantId);
        wrapper.eq(StringUtils.isNotBlank(pid), "pid", pid);
        wrapper.eq(StringUtils.isNotBlank(pids), "pids", pids);
        wrapper.eq(StringUtils.isNotBlank(typeNo), "type_no", typeNo);
        wrapper.eq(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(StringUtils.isNotBlank(value), "value", value);
        wrapper.eq(StringUtils.isNotBlank(remark), "remark", remark);
        wrapper.eq(StringUtils.isNotBlank(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotBlank(createDate), "create_date", createDate);
        wrapper.eq(StringUtils.isNotBlank(updater), "updater", updater);
        wrapper.eq(StringUtils.isNotBlank(updateDate), "update_date", updateDate);
        return wrapper;
    }

    @Override
    public Result saveEquipmentType(AssetsEquipmentTypeDTO dto) {
        Result result = new Result();
        Date date = new Date();

        //上级节点
        AssetsEquipmentTypeEntity assetsEquipmentTypePid = baseDao.selectByPid(dto.getPid());

        //参数业务校验
        result = this.saveEquipmentTypeCheck(dto, assetsEquipmentTypePid);
        if (!result.success()) {
            return result;
        }

        //生成类型编码 唯一
        //编号
        String typeNo = null;
        while (true) {
            typeNo = AssetsNoUtils.getEquipmentTypeNo();

            Integer noCountNum = baseDao.countByTypeNo(typeNo);
            if (noCountNum < 1) {
                break;
            }
        }
        dto.setTypeNo(typeNo);
        dto.setDel(Constant.DelStatus.NOT_DEL);
        dto.setTenantId(SecurityUser.getUser().getTenantId());
        dto.setCreateDate(date);
        dto.setCreator(SecurityUser.getUserId());
        dto.setPids(assetsEquipmentTypePid.getPids() + "," + assetsEquipmentTypePid.getId());
        dto.setLevel(assetsEquipmentTypePid.getLevel() + 1);
        dto.setPid(assetsEquipmentTypePid.getId());
        this.save(dto);
        return result;
    }

    @Override
    public Result updateEquipmentType(AssetsEquipmentTypeDTO dto) {
        Result result = new Result();
        Date date = new Date();
        //当前级别
        Integer currentLevel = dto.getLevel();
        //当前用户id
        Long userId = SecurityUser.getUserId();

        //上级节点
        AssetsEquipmentTypeEntity assetsEquipmentTypePid = baseDao.selectByPid(dto.getPid());
        //参数业务校验
        result = this.updateEquipmentTypeCheck(dto, assetsEquipmentTypePid);
        if (!result.success()) {
            return result;
        }

        dto.setLevel(assetsEquipmentTypePid.getLevel() + 1);
        dto.setUpdateDate(date);
        dto.setUpdater(userId);
        dto.setPid(assetsEquipmentTypePid.getId());
        dto.setPids(assetsEquipmentTypePid.getPids() + "," + assetsEquipmentTypePid.getId());
        this.update(dto);

        //如果变更的是2级节点   那下面的子节点也要进行变更
        if (currentLevel == 2) {
            List<AssetsEquipmentTypeEntity> assetsEquipmentTypeEntities = baseDao.getByPid(dto.getId());

            for (AssetsEquipmentTypeEntity assetsEquipmentTypeEntity : assetsEquipmentTypeEntities) {
                assetsEquipmentTypeEntity.setUpdateDate(date);
                assetsEquipmentTypeEntity.setUpdater(userId);
                assetsEquipmentTypeEntity.setLevel(dto.getLevel() + 1);
                assetsEquipmentTypeEntity.setPids(dto.getPids() + "," + dto.getId());
            }

            if (assetsEquipmentTypeEntities.size() > 0) {
                this.updateBatchById(assetsEquipmentTypeEntities);
            }
        }

        return result;
    }

    @Override
    public Result deleteEquipmentType(Long id) {

        Result result = new Result();
        Date date = new Date();

        //类别是否存在
        AssetsEquipmentTypeEntity assetsEquipmentTypeEntity = this.selectById(id);
        if (assetsEquipmentTypeEntity == null || assetsEquipmentTypeEntity.getDel().equals(Constant.DelStatus.IS_DEL)) {
            return result.error(ErrorCode.ASSETS_EQUIPMENT_TYPE_NOT_IN_50130);
        }

        //一级节点不能删除
        if (assetsEquipmentTypeEntity.getLevel() == 1) {
            return result.error(ErrorCode.FIRST_LEVEL_NO_AUTH_DEL_50131);
        }

        //当前节点下是否有子节点
        Integer childrenNum = baseDao.countByPid(assetsEquipmentTypeEntity.getId());
        if (childrenNum > 0) {
            return result.error(ErrorCode.HAS_CHILDREN_NODE_NOT_DEL_50135);
        }

        //当前节点 以及 子节点下 是否绑定了设备
        Integer deviceNum = assetsEquipmentService.countByAssetsEquipmentTypeId(id);
        if (deviceNum > 0) {
            return result.error(ErrorCode.TYPE_HAS_BIND_ASSETS_EQUIPMENT_50132);
        }

        assetsEquipmentTypeEntity.setDel(Constant.DelStatus.IS_DEL);
        assetsEquipmentTypeEntity.setUpdater(SecurityUser.getUserId());
        assetsEquipmentTypeEntity.setUpdateDate(date);
        this.updateById(assetsEquipmentTypeEntity);

        //如果是2级节点 则删除下属的所有子节点
        if (assetsEquipmentTypeEntity.getLevel() == 2) {
            baseDao.updateDelByPid(assetsEquipmentTypeEntity.getId());
        }

        return result;
    }

    @Override
    public Result saveEquipmentTypeCheck(AssetsEquipmentTypeDTO dto, AssetsEquipmentTypeEntity assetsEquipmentTypePid) {
        Result result = new Result();

        //上级节点是否存在
        if (assetsEquipmentTypePid == null || assetsEquipmentTypePid.getDel().equals(Constant.IsDel.YES.getValue())) {
            return result.error(ErrorCode.PID_IS_NOT_IN_50126);
        }
        //最多只支持3级
        if (assetsEquipmentTypePid.getLevel() == 3) {
            return result.error(ErrorCode.SUPPORT_AT_MOST_THREE_LEVEL_50127);
        }

        //類別名稱是否重複
        Integer typeNameNum = baseDao.countByNameAndId(dto.getName(), null, SecurityUser.getUser().getTenantId());
        if (typeNameNum > 0) {
            return result.error(ErrorCode.TYPE_NAME_IS_EXITS_50138);
        }

        return result;
    }

    @Override
    public Result updateEquipmentTypeCheck(AssetsEquipmentTypeDTO dto, AssetsEquipmentTypeEntity assetsEquipmentTypePid) {

        Result result = new Result();

        //一级节点不可修改
        if (dto.getLevel() == 1) {
            return result.error(ErrorCode.FIRST_LEVEL_NO_UPDATE_50128);
        }

        //上级节点是否存在
        if (assetsEquipmentTypePid == null || assetsEquipmentTypePid.getDel().equals(Constant.IsDel.YES.getValue())) {
            return result.error(ErrorCode.PID_IS_NOT_IN_50126);
        }

        //類別是否存在
        AssetsEquipmentTypeEntity assetsEquipmentTypeEntity = this.selectById(dto.getId());
        if (assetsEquipmentTypeEntity == null) {
            return result.error(ErrorCode.TYPE_NOT_IN_50133);
        }

        //挂載的上級不能為自己本身
        if (assetsEquipmentTypeEntity.getId().equals(assetsEquipmentTypePid.getId())) {
            return result.error(ErrorCode.IS_SELF_TYPE_50134);
        }

        //類別名稱是否重名
        Integer typeNameNum = baseDao.countByNameAndId(dto.getName(), dto.getId(), SecurityUser.getUser().getTenantId());
        if (typeNameNum > 0) {
            return result.error(ErrorCode.TYPE_NAME_IS_EXITS_50138);
        }

        //如果跟节点变动 本身以及下级节点有绑定设备也不让修改
        //当前节点 以及 子节点下 是否绑定了设备
        //当前节点的上级节点集合
        String[] currentNodeOldPidList = assetsEquipmentTypeEntity.getPids().split(",");
        //调整的新的上级节点的集合  如果当前上级节点只有一个则是一级节点 把他本身的节点 也存入
        String[] pidNodeOldPidList = assetsEquipmentTypePid.getPids().split(",");
        if (pidNodeOldPidList.length < 2) {
            pidNodeOldPidList = new String[2];
            pidNodeOldPidList[0] = assetsEquipmentTypePid.getPids();
            pidNodeOldPidList[1] = String.valueOf(assetsEquipmentTypePid.getId());

        }
        if (!currentNodeOldPidList[1].equals(pidNodeOldPidList[1])) {
            Integer deviceNum = assetsEquipmentService.countByAssetsEquipmentTypeId(dto.getId());
            if (deviceNum > 0) {
                return result.error(ErrorCode.CURRENT_OR_CHILDREN_TYPE_NAME_IS_EXITS_50139);
            }
        }




       /* //最多只支持3级
        if (assetsEquipmentTypePid.getLevel() == 3) {
            return result.error(ErrorCode.SUPPORT_AT_MOST_THREE_LEVEL_50127);
        }*/
        //挂载到2级节点下   本身节点下不能有子节点
        if (!assetsEquipmentTypeEntity.getPid().equals(assetsEquipmentTypePid.getId())) {
            if (assetsEquipmentTypePid.getLevel() == 2) {
                Integer childNum = baseDao.countByPid(dto.getId());
                if (childNum > 0) {
                    return result.error(ErrorCode.CHILD_NODE_EXCEED_THREE_LEVEL_50129);
                }
            }
        }


        return result;
    }

    @Override
    public List<AssetsEquipmentTypeTreeDTO> pageList() {
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();


        List<AssetsEquipmentTypeTreeDTO> list = baseDao.pageList(tenantId, Constant.EQUIPMENT_TYPE_INTERNET_OF_THINGS);

        return TreeUtils.build(list);
    }

    @Override
    public List<AssetsEquipmentTypeTreeDTO> listTreeSelect() {
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();

        List<AssetsEquipmentTypeTreeDTO> list = baseDao.listTreeSelect(tenantId, Constant.EQUIPMENT_TYPE_INTERNET_OF_THINGS);

        return TreeUtils.build(list);
    }

    @Override
    public List<AssetsEquipmentTypeTreeDTO> allTypeTree() {
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();

        List<AssetsEquipmentTypeTreeDTO> list = baseDao.allTypeTree(tenantId, Constant.EQUIPMENT_TYPE_INTERNET_OF_THINGS);

        return TreeUtils.build(list);
    }

    @Override
    public List<AssetsEquipmentTypeTreeDTO> universalTypeTree() {

        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();

        List<AssetsEquipmentTypeTreeDTO> list = baseDao.universalTypeTree(tenantId, Constant.EQUIPMENT_TYPE_UNIVERSAL);

        return TreeUtils.build(list);
    }
}

