package cn.amigosoft.modules.assets.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.ImportUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairDTO;
import cn.amigosoft.modules.assets.dto.AssetsTypeTreeData;
import cn.amigosoft.modules.assets.excel.AssetsEquipmentExcel;
import cn.amigosoft.modules.assets.excel.AssetsEquipmentImportExcel;
import cn.amigosoft.modules.assets.service.AssetsEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@RestController
@RequestMapping("assets/assetsequipment")
@Api(tags="资产设备表")
@Slf4j
public class AssetsEquipmentController {
    @Autowired
    private AssetsEquipmentService assetsEquipmentService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("assets:assetsequipment:page")
    public Result<PageData<AssetsEquipmentDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {

        PageData<AssetsEquipmentDTO> page = assetsEquipmentService.getAssetsPage(params);
        return new Result<PageData<AssetsEquipmentDTO>>().ok(page);

    }

    @GetMapping("IotPage")
    @ApiOperation("物联网设备分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("assets:assetsequipment:page")
    public Result<PageData<AssetsEquipmentDTO>> IotPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AssetsEquipmentDTO> page = assetsEquipmentService.getIotPage(params);
        return new Result<PageData<AssetsEquipmentDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("assets:assetsequipment:info")
    public Result<AssetsEquipmentDTO> get(@PathVariable("id") Long id) {
        AssetsEquipmentDTO data = assetsEquipmentService.getDetail(id);

        return new Result<AssetsEquipmentDTO>().ok(data);
    }

    @GetMapping("Iot/{id}")
    @ApiOperation("物联网设备详情")
    @RequiresPermissions("assets:assetsequipment:info")
    public Result<AssetsEquipmentDTO> getIot(@PathVariable("id") Long id) {
        AssetsEquipmentDTO data = assetsEquipmentService.getIotDetail(id);

        return new Result<AssetsEquipmentDTO>().ok(data);
    }

    @GetMapping("repair")
    @ApiOperation("维修记录")
    @RequiresPermissions("assets:assetsequipment:info")
    public Result<PageData<AssetsRepairDTO>> repairRecord(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AssetsRepairDTO> data = assetsEquipmentService.getRepairRecord(params);

        return new Result<PageData<AssetsRepairDTO>>().ok(data);

    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产设备表-保存")
    @RequiresPermissions("assets:assetsequipment:save")
    public Result save(@RequestBody AssetsEquipmentDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);


        assetsEquipmentService.saveAssets(dto);

        // 生成二维码标签
        assetsEquipmentService.setQrCodeUrl(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产设备表-修改")
    @RequiresPermissions("assets:assetsequipment:update")
    public Result update(@RequestBody AssetsEquipmentDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        String typeHead = dto.getCode().substring(0,2);
        if (Constant.AssetsEquipmentTypePrefix.ASSETS_EQUIPMENT_UNIVERSAL.equals(typeHead) && dto.getCurrentPersonStaffId() != null && dto.getStatus() == 2){
            AssetsEquipmentDTO dto1 = assetsEquipmentService.getStatus(dto.getId());
            Integer status =  dto1.getStatus();
            if (status == 1) {
                return new Result().error("该资产已被领用，请刷新页面");
            }
        }
        assetsEquipmentService.updateAssets(dto);

        // 生成二维码标签
        assetsEquipmentService.setQrCodeUrl(dto);

       return new Result();
    }

    @GetMapping("delete")
    @ApiOperation("删除")
    @LogOperation("资产设备表-删除")
    @RequiresPermissions("assets:assetsequipment:delete")
    public Result delete(Long id) {
        //效验数据
        if (id == null) {
            return new Result().error("参数异常");
        }
        AssetsEquipmentDTO assetsEquipmentDTO = assetsEquipmentService.get(id);
        assetsEquipmentDTO.setDel(Constant.AssetsEquipmentAllStatus.DEL);
        assetsEquipmentService.update(assetsEquipmentDTO);
        return new Result();
    }

    @GetMapping("status")
    @ApiOperation("设备状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资产ID", paramType = "query", required = true, dataType = "Long"),
    })
    @RequiresPermissions("assets:assetsequipment:info")
    public Result<AssetsEquipmentDTO> popInfo(Long id) {
        if (id == null) {
            return new Result().error("参数异常");
        }
        AssetsEquipmentDTO dto = assetsEquipmentService.getStatus(id);

        return new Result<AssetsEquipmentDTO>().ok(dto);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("资产设备表-导出")
    @RequiresPermissions("assets:assetsequipment:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AssetsEquipmentDTO> list = assetsEquipmentService.getExportList(params);

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        // 格式化然后放入字符串中
        String createDay = ft.format(dNow);

        ExcelUtils.exportExcelToTarget(response, "资产信息" + createDay, list, AssetsEquipmentExcel.class);
    }

    @GetMapping("typeTree")
    @ApiOperation("列表选择树")
    @RequiresPermissions("assets:assetsequipment:page")
    public Result<List<AssetsTypeTreeData>> assetsTypeTree() {
        List<AssetsTypeTreeData> assetsTypeTreeDataList = assetsEquipmentService.getAssetsTypeTree();
        return new Result<List<AssetsTypeTreeData>>().ok(assetsTypeTreeDataList);
    }

    @GetMapping("typeTreeGd")
    @ApiOperation("列表选择树")
    @RequiresPermissions("assets:assetsequipment:page")
    public Result<List<AssetsTypeTreeData>> assetsTypeTreeGd() {
        List<AssetsTypeTreeData> assetsTypeTreeDataList = assetsEquipmentService.getAssetsTypeTreeGd();
        return new Result<List<AssetsTypeTreeData>>().ok(assetsTypeTreeDataList);
    }

    @GetMapping("addTypeTree")
    @ApiOperation("新增资产类别选择树")
    @RequiresPermissions("assets:assetsequipment:page")
    public Result<List<AssetsTypeTreeData>> assetsAddTypeTree(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AssetsTypeTreeData> assetsTypeTreeDataList = assetsEquipmentService.getAssetsAddTypeTree(params);
        return new Result<List<AssetsTypeTreeData>>().ok(assetsTypeTreeDataList);
    }

    @GetMapping("downloadQRCodeZip")
    @ApiOperation("导出-标签批量下载")
    @LogOperation("资产设备表-导出-标签批量下载")
    @RequiresPermissions("assets:assetsequipment:export")
    public Result downloadQRCodeZip(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            assetsEquipmentService.downloadQRCodeZip(params, response, request);
        }catch (Exception e){
            throw new RenException("二维码压缩包下载失败");
        }
        return new Result();
    }
    @PostMapping("import")
    @ApiOperation(value = "上传文件")
    @RequiresPermissions("assets:assetsequipment:import")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        try {
            ImportParams params = new ImportParams();
            //导入的Excel表头行数
            params.setHeadRows(1);
            List<AssetsEquipmentImportExcel> assetsEquipmentImportExcels = ExcelImportUtil.importExcel(file.getInputStream(), AssetsEquipmentImportExcel.class, params);

            // 剔除空行数据
            AssetsEquipmentImportExcel emptyData = new AssetsEquipmentImportExcel();
            assetsEquipmentImportExcels = assetsEquipmentImportExcels.stream().filter(item -> !item.equals(emptyData)).collect(Collectors.toList());

            if (assetsEquipmentImportExcels == null || assetsEquipmentImportExcels.size() < 1) {
                ImportUtils.noDataImport(response);
                return;
            }
            List<AssetsEquipmentImportExcel> assetsEquipmentErrorImportExcels = new ArrayList<AssetsEquipmentImportExcel>();
            // 数据校验
            assetsEquipmentService.checkExcelDate(assetsEquipmentImportExcels, assetsEquipmentErrorImportExcels);

            if (assetsEquipmentErrorImportExcels.size() > 0) {
                String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
                String fileName = "资产导入错误信息-" + dataStr;
                ImportUtils.errorImport(assetsEquipmentImportExcels.size(), response, assetsEquipmentErrorImportExcels, AssetsEquipmentImportExcel.class, fileName);
            } else {
                ImportUtils.successImport(assetsEquipmentImportExcels.size(), response);
            }

        }catch (Exception e){
            log.error("导入资产服务异常", e);
            ImportUtils.errorParseExcel(response);
        }
    }

    @GetMapping("equipmentPage")
    @ApiOperation("资产领用-获取资产设备接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equipmentTypeId", value = "资产类别id", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "keyWord", value = "资产设备名或编号", paramType = "query", required = false, dataType="String") ,
    })
    @RequiresPermissions("assets:assetsequipment:equipmentPage")
    public Result<PageData<AssetsEquipmentDTO>> equipmentPage(@ApiIgnore @RequestParam Map<String, Object> params){
        if(params.get("equipmentTypeId")==null ){
            return new Result().error("资产类别为空");
        }
        PageData<AssetsEquipmentDTO> page = assetsEquipmentService.getEquipmentPage(params);

        return new Result<PageData<AssetsEquipmentDTO>>().ok(page);
    }

    @GetMapping("countByEquipmentType")
    @ApiOperation(value = "根据资产类别统计资产数量")
    @ApiImplicitParam(name = "equipmentTypeId", value = "资产类别id", paramType = "query", required = true, dataType="int")
    @RequiresPermissions("assets:assetsEquipmentType:delete")
    public Result<Integer> countByEquipmentType(@RequestParam(value = "equipmentTypeId",required = true) Long equipmentTypeId){
       Integer num=assetsEquipmentService.countByAssetsEquipmentTypeId(equipmentTypeId);
        return new Result<Integer>().ok(num);
    }
}