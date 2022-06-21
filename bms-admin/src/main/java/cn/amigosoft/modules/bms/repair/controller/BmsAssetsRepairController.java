package cn.amigosoft.modules.bms.repair.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.bms.repair.excel.BmsAssetsRepairExcel;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 资产维修表
 */
@RestController
@RequestMapping("bms/assetsRepair")
@Api(tags = "资产维修表 ")
public class BmsAssetsRepairController {
    @Autowired
    private BmsAssetsRepairService bmsAssetsRepairService;

    @GetMapping("page")
    @ApiOperation("报修申请分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsRepair:page")
    @LogOperation("报修申请-分页")
    public Result<PageData<BmsAssetsRepairDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsRepairService.getPage(params);
    }

    @GetMapping("verifyPage")
    @ApiOperation("报修审批分页")
    @LogOperation("报修审批-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsRepair:verifyPage")
    public Result<PageData<BmsAssetsRepairDTO>> verifyPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsRepairService.getVerifyPage(params);
    }

    @GetMapping("recordPage")
    @ApiOperation("报修记录分页")
    @LogOperation("报修记录-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsRepair:recordPage")
    public Result<PageData<BmsAssetsRepairDTO>> recordPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsRepairService.getRecordPage(params);
    }

    @GetMapping("statisticsPage")
    @ApiOperation("报修统计分页")
    @LogOperation("报修统计-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsRepair:page")
    public Result<PageData<BmsAssetsRepairDTO>> statisticsPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsRepairService.getStatisticsPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("维修记录-详情")
    @RequiresPermissions("bms:assetsRepair:info")
    public Result<BmsAssetsRepairDTO> get(@PathVariable("id") Long id) {
        BmsAssetsRepairDTO data = bmsAssetsRepairService.selectAssetsRepairById(id);

        return new Result<BmsAssetsRepairDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产信息-保存")
    @RequiresPermissions("bms:assetsRepair:save")
    public Result save(@RequestBody BmsAssetsRepairDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsRepairService.insertAssetsRepair(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产信息-修改")
    @RequiresPermissions("bms:assetsRepair:update")
    public Result update(@RequestBody BmsAssetsRepairDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsRepairService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产信息-删除")
    @RequiresPermissions("bms:assetsRepair:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsAssetsRepairEntity entity = new BmsAssetsRepairEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsAssetsRepairService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("applyExport")
    @ApiOperation("导出")
    @LogOperation("报修申请-导出")
    @RequiresPermissions("bms:assetsRepair:export")
    public void applyExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsRepairDTO> list = bmsAssetsRepairService.selectApplyExportList(params);
        ExcelUtils.exportExcelToTarget(response, "资产维修记录信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsRepairExcel.class);
    }

    @GetMapping("verifyExport")
    @ApiOperation("导出")
    @LogOperation("报修审批-导出")
    @RequiresPermissions("bms:assetsRepair:verifyExport")
    public void verifyExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsRepairDTO> list = bmsAssetsRepairService.selectVerifyExportList(params);
        ExcelUtils.exportExcelToTarget(response, "资产维修记录信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsRepairExcel.class);
    }

    @GetMapping("recordExport")
    @ApiOperation("导出")
    @LogOperation("报修记录-导出")
    @RequiresPermissions("bms:assetsRepair:recordExport")
    public void recordExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsRepairDTO> list = bmsAssetsRepairService.selectRecordExportList(params);
        ExcelUtils.exportExcelToTarget(response, "资产维修记录信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsRepairExcel.class);
    }

    @GetMapping("statisticsExport")
    @ApiOperation("导出")
    @LogOperation("报修统计-导出")
    @RequiresPermissions("bms:assetsRepair:export")
    public void statisticsExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsRepairDTO> list = bmsAssetsRepairService.selectStatisticsExportList(params);
        ExcelUtils.exportExcelToTarget(response, "资产维修记录信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsRepairExcel.class);
    }

}