package cn.amigosoft.modules.bms.dinning.controller;

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
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetail3Excel;
import cn.amigosoft.modules.bms.dinning.excel.BmsReceptionMealExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsReceptionMealService;
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
 * 接待餐表
 */
@RestController
@RequestMapping("bms/receptionMeal")
@Api(tags = "接待餐表 ")
public class BmsReceptionMealController {
    @Autowired
    private BmsReceptionMealService bmsReceptionMealService;

    @GetMapping("page")
    @ApiOperation("接待餐申请分页")
    @LogOperation("接待餐申请-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:receptionMeal:page")
    public Result<PageData<BmsReceptionMealDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsReceptionMealService.getPage(params);
    }

    @GetMapping("verifyPage")
    @ApiOperation("接待餐审核分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @LogOperation("接待餐审核-分页")
    @RequiresPermissions("bms:receptionMeal:verifyPage")
    public Result<PageData<BmsReceptionMealDTO>> verifyPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsReceptionMealService.getVerifyPage(params);
    }

    @GetMapping("statisticsPage")
    @ApiOperation("接待餐统计分页")
    @LogOperation("接待餐统计-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:receptionMeal:statisticsPage")
    public Result<PageData<BmsReceptionMealDTO>> statisticsPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsReceptionMealService.getStatisticsPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("接待餐-详情")
    @RequiresPermissions("bms:receptionMeal:info")
    public Result<BmsReceptionMealDTO> get(@PathVariable("id") Long id) {
        BmsReceptionMealDTO data = bmsReceptionMealService.selectReceptionDetail(id);

        return new Result<BmsReceptionMealDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("接待餐申请-保存")
    @RequiresPermissions("bms:receptionMeal:save")
    public Result save(@RequestBody BmsReceptionMealDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsReceptionMealService.saveReceptionMeal(dto);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("接待餐申请-修改")
    @RequiresPermissions("bms:receptionMeal:update")
    public Result update(@RequestBody BmsReceptionMealDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsReceptionMealService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("接待餐申请-删除")
    @RequiresPermissions("bms:receptionMeal:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsReceptionMealEntity entity = new BmsReceptionMealEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsReceptionMealService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("applyExport")
    @ApiOperation("导出")
    @LogOperation("接待餐申请-导出")
    @RequiresPermissions("bms:receptionMeal:export")
    public void applyExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.selectApplyExportList(params);

        ExcelUtils.exportExcelToTarget(response, "接待餐信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsReceptionMealExcel.class);
    }

    @GetMapping("verifyExport")
    @ApiOperation("导出")
    @LogOperation("接待餐审批-导出")
    @RequiresPermissions("bms:receptionMeal:export")
    public void verifyExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.selectVerifyExportList(params);

        ExcelUtils.exportExcelToTarget(response, "接待餐信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsReceptionMealExcel.class);
    }

    @GetMapping("statisticsExport")
    @ApiOperation("导出")
    @LogOperation("接待餐统计-导出")
    @RequiresPermissions("bms:receptionMeal:export")
    public void statisticsExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.selectStatisticsExport(params);

        ExcelUtils.exportExcelToTarget(response, "接待餐统计信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsReceptionMealExcel.class);
    }

    @GetMapping("countPage")
    @ApiOperation("接待餐人数统计分页")
    @LogOperation("接待餐人数统计-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:receptionMeal:countPage")
    public Result<PageData<BmsReceptionMealDTO>> countPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsReceptionMealService.getCountPage(params);
    }

    @GetMapping("countExport")
    @ApiOperation("导出")
    @LogOperation("接待餐人数统计-导出")
    @RequiresPermissions("bms:receptionMeal:countExport")
    public void countExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.selectCountExport(params);

        ExcelUtils.exportExcelToTarget(response, "接待餐人数统计信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsOrderDetail3Excel.class);
    }

}