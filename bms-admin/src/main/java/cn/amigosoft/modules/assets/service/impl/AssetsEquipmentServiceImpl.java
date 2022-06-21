package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.utils.ResourceDownLoadUtils;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.*;
import cn.amigosoft.modules.assets.dao.AssetsEquipmentDao;
import cn.amigosoft.modules.assets.dto.*;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentEntity;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentTypeEntity;
import cn.amigosoft.modules.assets.excel.AssetsEquipmentImportExcel;
import cn.amigosoft.modules.assets.service.AssetsEquipmentService;
import cn.amigosoft.modules.assets.service.AssetsEquipmentTypeService;
import cn.amigosoft.modules.assets.utils.CreateImgUtils;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@Service
public class AssetsEquipmentServiceImpl extends CrudServiceImpl<AssetsEquipmentDao, AssetsEquipmentEntity, AssetsEquipmentDTO> implements AssetsEquipmentService {

    @Autowired
    private AssetsEquipmentTypeService equipmentTypeService;

    @Override
    public QueryWrapper<AssetsEquipmentEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AssetsEquipmentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<AssetsEquipmentDTO> getAssetsPage(Map<String, Object> params) {  //台账列表查询

        //当前租户id
//        Long tenantId = SecurityUser.getUser().getTenantId();
//        params.put("tenantId",tenantId);
        IPage<AssetsEquipmentEntity> page = getPage(params, "",false);
        List<AssetsEquipmentDTO> list = baseDao.getAssetsPage(params);
        return getPageData(list, page.getTotal(), AssetsEquipmentDTO.class);
    }

    @Override
    public AssetsEquipmentDTO getDetail(Long id) {
        return baseDao.getDetail(id);
    }
    @Override
    public PageData<AssetsEquipmentDTO> getIotPage(Map<String, Object> params) {
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();
        params.put("tenantId",tenantId);
        IPage<AssetsEquipmentEntity> page = getPage(params,"",false);
        List<AssetsEquipmentDTO> list = baseDao.getIotPage(params);
        return getPageData(list,page.getTotal(),AssetsEquipmentDTO.class);
    }

    @Override
    public PageData<AssetsEquipmentDTO> getAssetsOnPlan(Map<String, Object> params) {
        IPage<AssetsEquipmentEntity> page = getPage(params,"a.id",false);
        List<AssetsEquipmentDTO> list = baseDao.selectAssetsByParamsOnPlan(params);
        return getPageData(list,page.getTotal(),AssetsEquipmentDTO.class);
    }

    @Override
    public PageData<AssetsEquipmentDTO> getAssetsIotOnPlan(Map<String, Object> params) {
        IPage<AssetsEquipmentEntity> page = getPage(params,"a.id",false);
        List<AssetsEquipmentDTO> list = baseDao.selectAssetsIotByParamsOnPlan(params);
        return getPageData(list,page.getTotal(),AssetsEquipmentDTO.class);
    }

    @Override
    public AssetsEquipmentDTO getIotDetail(Long id) {     //物联网设备详情
        AssetsEquipmentDTO assetsEquipmentDTO = baseDao.getIotDetail(id);
        return assetsEquipmentDTO;
    }

    @Override
    public void saveAssets(AssetsEquipmentDTO dto) {   //新增资产
        //判断新增的资产类型（固定或通用）
        String assetCode = "";   //资产编码
        Long id = dto.getAssetsEquipmentTypeId();       //获取当前新增资产的资产类型ID
        if (id != null) {
            AssetsPidNames assetsPidNames = baseDao.getAssetsTypesById(id);
            if(Constant.AssetsEquipmentTypeId.ASSETS_TYPE_FIXED.equals(assetsPidNames.getPids().split(",")[1])){ // 固定资产
                assetCode = Constant.AssetsEquipmentTypePrefix.ASSETS_EQUIPMENT_FIXED + AssetsNoUtils.getUniqueId();
                dto.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_USING);   //新增的固定资产默认在用
            }
            if(Constant.AssetsEquipmentTypeId.ASSETS_TYPE_UNIVARSAL.equals(assetsPidNames.getPids().split(",")[1])){  // 通用资产
                assetCode = Constant.AssetsEquipmentTypePrefix.ASSETS_EQUIPMENT_UNIVERSAL + AssetsNoUtils.getUniqueId();
                if(dto.getCurrentPersonStaffId() != null){    //若存在当前使用人，该通用设备为在用状态
                    dto.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_USING);
                } else {
                    dto.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_FREE); //否则为闲置状态
                }
            }
        }
        if (id == null) {
            dto.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_USING);
        }
        dto.setMaintainStatus(Constant.AssetsEquipmentAllStatus.MAINTAIN_STATUS_NORMAL);
        if(StringUtil.isBlank(assetCode)) { //物联网设备
            assetCode = Constant.AssetsEquipmentTypePrefix.ASSETS_INTERNET_OF_THINGS + AssetsNoUtils.getUniqueId();
        }
        dto.setCode(assetCode);
        this.save(dto);
    }

    @Override
    public void updateAssets(AssetsEquipmentDTO dto) {
        this.update(dto);
    }

    @Override
    public PageData<AssetsRepairDTO> getRepairRecord(Map<String, Object> params) {  //维修记录

        IPage<AssetsEquipmentEntity> page = getPage(params, "", true);

        List<AssetsRepairDTO> list = baseDao.getRepairRecord(params);

        return getPageData(list, page.getTotal(), AssetsRepairDTO.class);

    }

    @Override
    public List<AssetsEquipmentDTO> getExportList(Map<String, Object> params) {   //获取导出列表
        return baseDao.getExportList(params);
    }

    @Override
    public List<AssetsEquipmentDTO> getIotExportList(Map<String, Object> params) {  //获取物联网设备导出列表
        return baseDao.getIotExportList(params);
    }

    @Override
    public Integer countByAssetsEquipmentTypeId(Long id) {
        return baseDao.countByAssetsEquipmentTypeId(id);
    }

    @Override
    public PageData<AssetsEquipmentDTO> getEquipmentPage(Map<String, Object> params) {
        paramsToLike(params,"keyWord");

        //获取相关类别及其子类的类别id
        List<AssetsEquipmentTypeEntity> entityList = equipmentTypeService.selectList(new LambdaQueryWrapper<AssetsEquipmentTypeEntity>()
                .like(AssetsEquipmentTypeEntity::getPids,params.get("equipmentTypeId"))
                .select(AssetsEquipmentTypeEntity::getId)
        );
        List<Long> typeIdsList = entityList.stream().map(AssetsEquipmentTypeEntity::getId).collect(Collectors.toList());
        typeIdsList.add(Long.valueOf(params.get("equipmentTypeId").toString()) );
        Long[]typeIds = typeIdsList.toArray(new Long[]{});
        params.put("typeIds",typeIds);
        params.put("del",Constant.AssetsEquipmentAllStatus.NOT_DEL);
        params.put("status",Constant.AssetsEquipmentAllStatus.STATUS_FREE);//资产状态：闲置
        params.put("maintaintStatus",Constant.AssetsEquipmentAllStatus.MAINTAIN_STATUS_NORMAL);//维修状态：正常
        IPage<AssetsEquipmentEntity> page = getPage(params, Constant.CREATE_DATE, false);
        List<AssetsEquipmentDTO> list = baseDao.getEquipmentPage(page,params);
        return getPageData(list, page.getTotal(), AssetsEquipmentDTO.class);
    }

    @Override
    public List<AssetsTypeTreeData> getAssetsTypeTree() {   //列表选择树
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();
        List<AssetsTypeTreeData> assetsTypeTreeDataList = baseDao.getAssetsTypeTree(tenantId);
        return TreeUtils.build(assetsTypeTreeDataList);
    }

    @Override
    public List<AssetsTypeTreeData> getAssetsTypeTreeGd() {   //列表选择树
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();
        List<AssetsTypeTreeData> assetsTypeTreeDataList = baseDao.getAssetsTypeTreeGd(tenantId);
        return TreeUtils.build(assetsTypeTreeDataList);
    }

    @Override
    public List<AssetsTypeTreeData> getAssetsAddTypeTree(Map<String, Object> params) {  //新增选择树（固定与通用）
        //当前租户id
        Long tenantId = SecurityUser.getUser().getTenantId();
        params.put("tenantId", tenantId);
        List<AssetsTypeTreeData> assetsTypeTreeDataList = baseDao.getAssetsAddTypeTree(params);
        return TreeUtils.build(assetsTypeTreeDataList);
    }

    @Override
    public void setQrCodeUrl(AssetsEquipmentDTO dto) {
        String qrCodeUrl = this.getQrCodeUrl(dto);
        if(!StringUtil.isBlank(qrCodeUrl)){
            dto.setQrCodeUrl(qrCodeUrl);
            this.update(dto);
        }
    }

    @Override
    public void downloadQRCodeZip(Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {
        String assetsType = (String)params.get("assetsType");
        List<AssetsEquipmentDTO> exportList = null;
        if ("2".equals(assetsType)) {
            exportList = this.getIotExportList(params);
        }
        if (!"2".equals(assetsType)) {
            exportList = this.getExportList(params);
        }
        if(exportList == null || exportList.size() < 1){
            return;
        }
        List<String> name = new ArrayList();
        ArrayList<String> urls = new ArrayList<>();
        //1.获取需要下载的二维码地址数组
        for (AssetsEquipmentDTO assetsEquipmentDTO : exportList) {
            if(!StringUtil.isBlank(assetsEquipmentDTO.getQrCodeUrl())){
                urls.add(assetsEquipmentDTO.getQrCodeUrl());
                name.add(assetsEquipmentDTO.getCode() + "-" + assetsEquipmentDTO.getName());
            }
        }
        String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
        //2.开始批量下载功能
        ResourceDownLoadUtils.downloadZipForPatrolPolicy(response,urls,name,"资产标签" + dataStr);

    }

    @Override
    public List<AssetsEquipmentDTO> getExpireEquipmentList() {
        return baseDao.getExpireEquipmentList();
    }

    @Override
    public void checkExcelDate(List<AssetsEquipmentImportExcel> excelDate, List<AssetsEquipmentImportExcel> excelErrorDate) {
        // 获取资产类型
        UserDetail superTenant = SecurityUser.getUser();
        List<AssetsPidNames> pidNamesByAssetsType = baseDao.getPidNamesByAssetsType(superTenant.getTenantId());
        List<AssetsPidNames> pidNamesByArea = baseDao.getPidNamesByArea();
        List<AssetsPidNames> pidNamesByDept = baseDao.getPidNamesByDept();
        // 必要数据验证
        for (AssetsEquipmentImportExcel excel : excelDate) {
            AssetsEquipmentDTO assetsEquipmentDTO = new AssetsEquipmentDTO();
            String errText = "";
            Boolean flag = true;
            if(StringUtil.isBlank(excel.getName())){
                errText = errText + "资产名称不能为空；";
                flag = false;
            }else{
                assetsEquipmentDTO.setName(excel.getName());
            }
            if(StringUtil.isBlank(excel.getVendor())){
                errText = errText + "设备厂商不能为空；";
                flag = false;
            }else{
                assetsEquipmentDTO.setVendor(excel.getVendor());
            }
            if(StringUtil.isBlank(excel.getModel())){
                errText = errText + "规格型号不能为空；";
                flag = false;
            }else{
                assetsEquipmentDTO.setModel(excel.getModel());
            }
            if(StringUtil.isBlank(excel.getPurchaseTimeStr())){
                errText = errText + "购置日期不能为空；";
                flag = false;
            }else{
                Date parse = DateUtils.parse(excel.getPurchaseTimeStr(), DateUtils.DATE_PATTERN);
                if(parse == null){
                    Date parse1 = DateUtils.parse(excel.getPurchaseTimeStr(), DateUtils.DATE_DAY_PATTERN_LINE);
                    if(parse1 == null){
                        errText = errText + "购置日期时间格式错误；";
                        flag = false;
                    }else{
                        assetsEquipmentDTO.setPurchaseTime(parse1);
                    }
                }else{
                    assetsEquipmentDTO.setPurchaseTime(parse);
                }
            }

            assetsEquipmentDTO.setPosition(excel.getPosition());
            assetsEquipmentDTO.setRemark(excel.getRemark());
            assetsEquipmentDTO.setPurchaseAmount(excel.getPurchaseAmount());
            if(!StringUtil.isBlank(excel.getEnableTimeStr())){
                Date parse = DateUtils.parse(excel.getEnableTimeStr(), DateUtils.DATE_PATTERN);
                if(parse == null){
                    Date parse1 = DateUtils.parse(excel.getEnableTimeStr(), DateUtils.DATE_DAY_PATTERN_LINE);
                    if(parse1 == null){
                        errText = errText + "启用日期时间格式错误；";
                        flag = false;
                    }else{
                        assetsEquipmentDTO.setEnableTime(parse1);
                    }
                }else{
                    assetsEquipmentDTO.setEnableTime(parse);
                }
            }
            assetsEquipmentDTO.setUseLimit(excel.getUseLimit());
            if(excel.getYearsRemind() != null){
                assetsEquipmentDTO.setYearsRemind(excel.getYearsRemind());
            }else{
                assetsEquipmentDTO.setYearsRemind(1); //默认关闭提醒
            }
            if(excel.getAdvanceNums() != null){
                if(excel.getAdvanceNums() > 100){
                    errText = errText + "提前提醒天数不超过100天；";
                    flag = false;
                }else{
                    assetsEquipmentDTO.setAdvanceNums(excel.getAdvanceNums());
                }
            }
            String codeType = "";
            // 资产类别
            if(StringUtil.isBlank(excel.getAssetsEquipmentTypeNames())){
                errText = errText + "资产类别不能为空；";
                flag = false;
            }else{
                Boolean typeFlag = false;
                for (AssetsPidNames assetsPidNames : pidNamesByAssetsType) {
                    if(excel.getAssetsEquipmentTypeNames().trim().equals(assetsPidNames.getNames())){
                        assetsEquipmentDTO.setAssetsEquipmentTypeId(assetsPidNames.getId());
                        typeFlag = true;
                        if(Constant.AssetsEquipmentTypeId.ASSETS_TYPE_FIXED.equals(assetsPidNames.getPids().split(",")[1])){
                            codeType = Constant.AssetsEquipmentTypePrefix.ASSETS_EQUIPMENT_FIXED; // 固定资产
                            assetsEquipmentDTO.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_USING);
                        }
                        if(Constant.AssetsEquipmentTypeId.ASSETS_TYPE_UNIVARSAL.equals(assetsPidNames.getPids().split(",")[1])){
                            codeType = Constant.AssetsEquipmentTypePrefix.ASSETS_EQUIPMENT_UNIVERSAL; // 通用资产
                            assetsEquipmentDTO.setStatus(Constant.AssetsEquipmentAllStatus.STATUS_FREE);
                        }
                        break;
                    }
                }
                if(!typeFlag){
                    errText = errText + "资产类别不存在；";
                    flag = false;
                }
            }
            // 所在区域
            if(StringUtil.isBlank(excel.getAreaNames())){
                errText = errText + "所属区域不能为空；";
                flag = false;
            }else{
                Boolean areaFlag = false;
                for (AssetsPidNames assetsPidNames : pidNamesByArea) {
                    if(excel.getAreaNames().trim().equals(assetsPidNames.getNames())){
                        assetsEquipmentDTO.setAreaId(assetsPidNames.getId());
                        areaFlag = true;
                        break;
                    }
                }
                if(!areaFlag){
                    errText = errText + "所属区域不存在；";
                    flag = false;
                }
            }
            // 管理部门
            if(StringUtil.isBlank(excel.getDeptNames())){
                errText = errText + "管理部门不能为空；";
                flag = false;
            }else{
                Boolean deptFlag = false;
                for (AssetsPidNames assetsPidNames : pidNamesByDept) {
                    if(excel.getDeptNames().trim().equals(assetsPidNames.getNames())){
                        assetsEquipmentDTO.setDeptId(assetsPidNames.getId());
                        deptFlag = true;
                        break;
                    }
                }
                if(!deptFlag){
                    errText = errText + "管理部门不存在；";
                    flag = false;
                }
            }
            // 责任人(无法区分唯一，暂不导入)
            // 当前领用人(无法区分唯一，暂不导入)

            if(flag){
                // 资产编号

                String code = "";
                code = codeType + AssetsNoUtils.getUniqueId();
                assetsEquipmentDTO.setCode(code);
                assetsEquipmentDTO.setMaintainStatus(Constant.AssetsEquipmentAllStatus.MAINTAIN_STATUS_NORMAL);
                this.save(assetsEquipmentDTO);
                this.setQrCodeUrl(assetsEquipmentDTO);
            }else{
                excel.setErrorImport(errText);
                excelErrorDate.add(excel);
            }
        }
    }

    private String getQrCodeUrl(AssetsEquipmentDTO assetsEquipmentDTO){
        try {
            String content = assetsEquipmentDTO.getCode();
            String url = createCommonQRCode(content, null);
            Long deviceId = assetsEquipmentDTO.getDeviceId();
            if (deviceId != null) {
                AssetsEquipmentDTO newDto = this.getIotDetail(deviceId);
                assetsEquipmentDTO.setCode(newDto.getCode());
                assetsEquipmentDTO.setAreaName(newDto.getAreaName());
                assetsEquipmentDTO.setName(newDto.getName());
            }
            if (deviceId == null && assetsEquipmentDTO.getAreaId() != null) {
                Long areaId = assetsEquipmentDTO.getAreaId();
                AssetsEquipmentDTO dto = baseDao.getAreaName(areaId);
                assetsEquipmentDTO.setAreaName(dto.getAreaName());
            }
            System.out.println("二维码url:" + url);
            String person = "";
            if(!StringUtil.isBlank(assetsEquipmentDTO.getLiabilityPerson())){
                person = assetsEquipmentDTO.getLiabilityPerson().split(",")[0];
            }
            String assetsCode  ="";
            if(!StringUtil.isBlank(assetsEquipmentDTO.getCode())){
                assetsCode = assetsEquipmentDTO.getCode();
            }
            String area = "";
            if(!StringUtil.isBlank(assetsEquipmentDTO.getAreaName())){
                area = assetsEquipmentDTO.getAreaName();
            }
            String a = "<table style='font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;'>\n" +
                    "\t\t\t<tr>\n" +
                    "\t\t\t\t<td colspan='2' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                    "\t\t\t\t\t责任人："+ person +"</td>\n" +
                    "\t\t\t</tr>\n" +
                    "\t\t\t<tr>\n" +
                    "\t\t\t\t<td colspan='2' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                    "\t\t\t\t\t资产名称："+ assetsEquipmentDTO.getName() +"</td>\n" +
                    "\t\t\t</tr>\n" +
                    "\t\t\t<tr>\n" +
                    "\t\t\t\t<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                    "\t\t\t\t\t资产编号："+ assetsCode +"</td>\n" +
                    "\t\t\t\t<td rowspan=\"2\" style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                    "\t\t\t\t\t<img src='" + url +"' style='width: 50px;'>\t\n" +
                    "\t\t\t\t</td>\n" +
                    "\t\t\t</tr>\n" +
                    "\t\t\t<tr>\n" +
                    "\t\t\t\t<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                    "\t\t\t\t\t所在区域："+ area +"</td>\n" +
                    "\t\t\t</tr>\n" +
                    "\t\t</table>";
            String s = CreateImgUtils.getcreateImgUrl(a);
            return s;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String createCommonQRCode(String content,String bottomDes) throws Exception {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        //使用工具类生成二维码
        BufferedImage imageBySize = QrCodeUtil.createImageBySize(content, null, true, null, 100);
        ImageIO.write(imageBySize, "JPG", bOut);
        bOut.flush();
        byte[] imageInByte = bOut.toByteArray();
        bOut.close();
        String url = "";
        if(imageInByte.length>0){
            url = OSSFactory.build().uploadSuffix(imageInByte,"jpg","jpg");
        }
        return url;
    }

    @Override
    public AssetsEquipmentDTO getStatus(Long id) {
        return baseDao.getStatus(id);
    }
}