package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.assets.dao.AssetsRepairDao;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairDetailDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairPageListDTO;
import cn.amigosoft.modules.assets.entity.AssetsRepairDealEntity;
import cn.amigosoft.modules.assets.entity.AssetsRepairEntity;
import cn.amigosoft.modules.assets.service.AssetsEquipmentService;
import cn.amigosoft.modules.assets.service.AssetsRepairDealService;
import cn.amigosoft.modules.assets.service.AssetsRepairService;
import cn.amigosoft.modules.person.entity.PersonEntity;
import cn.amigosoft.modules.person.service.PersonService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  资产维修表 服务实现类
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
@Service
public class AssetsRepairServiceImpl extends CrudServiceImpl<AssetsRepairDao, AssetsRepairEntity, AssetsRepairDTO> implements AssetsRepairService {

    @Autowired
    private PersonService personService;

    @Autowired
    private AssetsRepairDealService assetsRepairDealService;

    @Autowired
    private AssetsEquipmentService assetsEqumentService;

    @Override
    public QueryWrapper<AssetsRepairEntity> getWrapper(Map<String, Object> params) {
        String assetsName = (String) params.get("assets_name");
        String status = (String) params.get("status");
        String creator = (String) params.get("creator");
        QueryWrapper<AssetsRepairEntity> wrapper = new QueryWrapper<>();

        wrapper.like(!StringUtil.isEmpty(assetsName), "ae.name", assetsName);
        wrapper.eq(StringUtils.isNotBlank(status), "ar.status", status);
        wrapper.like(StringUtils.isNotBlank(creator), "p.name", creator);
        wrapper.eq("p.del", 0);
        wrapper.isNotNull("ps.id");
        return wrapper;
    }

    @Override
    public PageData<AssetsRepairPageListDTO> getAssetsRepairPageList(Map<String, Object> params) {
        Page page = (Page) this.getPage(params, "ar.create_date", false);
        QueryWrapper wrapper = this.getWrapper(params);
        List<AssetsRepairPageListDTO> assetsRepairPageListDTO = baseDao.getAssetsRepairPageList(page, wrapper);
        PageData<AssetsRepairPageListDTO> pageData = getPageData(assetsRepairPageListDTO, page.getTotal(), AssetsRepairPageListDTO.class);
        return pageData;
    }

    @Override
    public AssetsRepairDetailDTO getAssetsRepairDetail(Long id){
        AssetsRepairDetailDTO assetsRepairDetailDTO = baseDao.getAssetsRepairDetail(id);
        if(!StringUtil.isEmpty(assetsRepairDetailDTO)){
            //直接处理获取处理人信息(user_id|app_user_id)
            if(!StringUtil.isEmpty(assetsRepairDetailDTO.getIsDispatch())){
                QueryWrapper<PersonEntity> qw =  new QueryWrapper<>();
                qw.eq("id",assetsRepairDetailDTO.getDealPersonId());

                PersonEntity personEntity = personService.selectOne(qw);
                if(!StringUtil.isEmpty(personEntity)){
                    assetsRepairDetailDTO.setDealPersonName(personEntity.getName());
                    assetsRepairDetailDTO.setDealPerosnPhone(personEntity.getPhone());
                }
            }
        }
        return assetsRepairDetailDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result dealAssetsRepair(AssetsRepairDTO assetsRepairDTO){
        Result result = new Result();

        //禁止租户账号操作
        PersonEntity personEntity =  personService.selectOne(
                new QueryWrapper<PersonEntity>().eq("sys_user_id", SecurityUser.getUserId()).eq("del", Constant.DelStatus.NOT_DEL));


        //避免重复指派
        AssetsRepairEntity oldAssetsRepair = baseDao.selectById(assetsRepairDTO.getId());
        Long dealPerosonId = assetsRepairDTO.getDealPersonId();
        Result errorResult = this.vaildateDealAssetsRepair(oldAssetsRepair,assetsRepairDTO);
        if(!errorResult.success()){
            return  errorResult;
        }

        Map<String,Object> params = new HashMap<>();
        Integer status =  Constant.AssetesRepairStaus.STATUS_WORKING;
        if(!StringUtil.isEmpty(assetsRepairDTO.getDealResult())){
            status =  Constant.AssetesRepairStaus.STATUS_EVALUATED;
            params.put("dealDate",new Date());
        }

        //直接处理维修工单
        if(assetsRepairDTO.getIsDispatch() == 0){
            if (StringUtil.isEmpty(personEntity)){
                return result.error("该用户未非员工账号,请先绑定员工");
            }
            //将app_user_id或者sys_user_id转为peron_id
           if(!StringUtil.isEmpty(personEntity)){
               dealPerosonId = personEntity.getId();
           }
        }else{
            // TODO 添加判断
//            PersonStaffEntity personStaffEntity = personStaffService.selectById(dealPerosonId);
//            if(!StringUtil.isEmpty(personStaffEntity)){
//                dealPerosonId = personStaffEntity.getPersonId();
//            }else{
                return result.error("不存在该工作人员,无法指派");
//            }
        }

        params.put("id",assetsRepairDTO.getId());
        params.put("dealResult",assetsRepairDTO.getDealResult());
        params.put("dealImgs",assetsRepairDTO.getDealImgs());
        params.put("isDispath",assetsRepairDTO.getIsDispatch());
        params.put("updater",SecurityUser.getUserId());
        params.put("status", status);
        params.put("dealPersonId",dealPerosonId);
        //1.更新资产维修表
        baseDao.dealAssetsRepair(params);

        //插入资产维修处理表
        AssetsRepairDealEntity assetsRepairDealEntity = new AssetsRepairDealEntity();
        assetsRepairDealEntity.setAssetsRepairId(assetsRepairDTO.getId());
        assetsRepairDealEntity.setImgs(assetsRepairDTO.getDealImgs());
        assetsRepairDealEntity.setResult(assetsRepairDTO.getDealResult());
        assetsRepairDealEntity.setStatus(status);
        assetsRepairDealEntity.setToPersonId(dealPerosonId);
        assetsRepairDealEntity.setIsDispatch(assetsRepairDTO.getIsDispatch());
        assetsRepairDealEntity.setCreator(SecurityUser.getUser().getId());

        //2.插入资产维修处理表
        assetsRepairDealService.insert(assetsRepairDealEntity);

        //3.变更设备状态
        this.changeEqumentStatus(oldAssetsRepair.getAssetsEquipmentId(),status);
        return result;
    }

    public Result vaildateDealAssetsRepair(AssetsRepairEntity oldAssetsRepair,AssetsRepairDTO assetsRepairDTO){
        Result result = new Result();
        if(oldAssetsRepair.getStatus() == Constant.AssetesRepairStaus.STATUS_CANCEL){
            return result.error("该资产维修已取消无法操作");
        }
        if(!StringUtil.isEmpty(oldAssetsRepair.getDealPersonId())){
            //直接处理维修工单
            if (assetsRepairDTO.getIsDispatch() == 0) {
                if (StringUtil.isEmpty(assetsRepairDTO.getDealResult())) {
                    return result.error("资产维修结果不能为空");
                }
                if ( oldAssetsRepair.getDealPersonId().equals(assetsRepairDTO.getDealPersonId())) {
                    return result.error("已为该资产维修配置此人员,请勿重复操作");
                }
            } else {
                //处理中
                if(oldAssetsRepair.getStatus() == Constant.AssetesRepairStaus.STATUS_WORKING){
                    if(oldAssetsRepair.getDealPersonId().equals(assetsRepairDTO.getDealPersonId())){
                        if(StringUtil.isEmpty(oldAssetsRepair.getDealResult()) && StringUtil.isEmpty(assetsRepairDTO.getDealResult())){
                            return result.error("资产维修结果不能为空");
                        }
                    }
                }
            }

            //避免重复提交维修成果
            if(oldAssetsRepair.getStatus() == Constant.AssetesRepairStaus.STATUS_EVALUATED){
                return result.error("该资产维修已结束,请勿重复提交维修结果");
            }
        }
        return  result;
    }

    @Override
    public Result evaluateAssetsRepair(AssetsRepairDTO assetsRepairDTO){
        Result result = new Result();
        //禁止租户账号操作
        PersonEntity personEntity =  personService.selectOne(
                new QueryWrapper<PersonEntity>().eq("sys_user_id",SecurityUser.getUserId()).eq("del",Constant.DelStatus.NOT_DEL));
        if (StringUtil.isEmpty(personEntity)){
            return result.error("该用户未非员工账号,请先绑定员工");
        }

        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isEmpty(assetsRepairDTO.getGrade())){
            result.error("资产维修评分不能为空");
        }

        AssetsRepairEntity oldAssetsRepair = baseDao.selectById(assetsRepairDTO.getId());
        if(oldAssetsRepair.getStatus() == Constant.AssetesRepairStaus.STATUS_FINISHED){
            return result.error("该资产维修已结束,请勿评分");
        }

        params.put("grade",assetsRepairDTO.getGrade());
        params.put("gradeDesc",assetsRepairDTO.getGradeDesc());
        params.put("status", Constant.AssetesRepairStaus.STATUS_FINISHED);
        baseDao.evaluateAssetsRepair(params);

        return result;
    }


    public void changeEqumentStatus(Long id,Integer repairStatus){
        Integer maintainStatus = 0;
        AssetsEquipmentDTO assetsEquipmentDTO = assetsEqumentService.get(id);
        if(!StringUtil.isEmpty(assetsEquipmentDTO)){
            //正在报修中
            if(repairStatus == 0){
                maintainStatus = 1;
            }
            //指派
            if (repairStatus == Constant.AssetesRepairStaus.STATUS_WORKING) {
                maintainStatus = 1;
            }
            //待评价--标志实际结束
            if(repairStatus == Constant.AssetesRepairStaus.STATUS_EVALUATED){
                maintainStatus = 0;
            }
            //已取消
            if(repairStatus == Constant.AssetesRepairStaus.STATUS_CANCEL){
                maintainStatus = 0;
            }
            assetsEquipmentDTO.setMaintainStatus(maintainStatus);
            assetsEqumentService.update(assetsEquipmentDTO);
        }
    }
}
