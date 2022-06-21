package cn.amigosoft.modules.bms.visit.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.bms.visit.excel.BmsVisitApplyExcel;
import cn.amigosoft.modules.bms.visit.service.BmsVisitApplyService;
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
 * 人员报备申请表
 */
@RestController
@RequestMapping("bms/visitApply")
@Api(tags = "人员报备申请表 ")
public class BmsVisitApplyController {

    @Autowired
    private BmsVisitApplyService bmsVisitApplyService;

    @GetMapping("applyPage")
    @ApiOperation("人员报备申请分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:visitApply:page")
    public Result<PageData<BmsVisitApplyDTO>> applyPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsVisitApplyService.getApplyPage(params);
    }

    @GetMapping("page")
    @ApiOperation("人员报备审批分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:visitApplyVerify:page")
    public Result<PageData<BmsVisitApplyDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsVisitApplyService.getPage(params);
    }

    @GetMapping("approvePage")
    @ApiOperation("人员报备记录分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:visitApplyRecord:page")
    public Result<PageData<BmsVisitApplyDTO>> approvePage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsVisitApplyService.getApprovePage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:visitApply:info")
    public Result<BmsVisitApplyDTO> get(@PathVariable("id") Long id) {
        BmsVisitApplyDTO data = bmsVisitApplyService.selectVisitApplyById(id);

        return new Result<BmsVisitApplyDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("人员报备申请表 -保存")
    @RequiresPermissions("bms:visitApply:save")
    public Result save(@RequestBody BmsVisitApplyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsVisitApplyService.insertVisitApply(dto);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("人员报备申请表 -删除")
    @RequiresPermissions("bms:visitApply:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsVisitApplyEntity entity = new BmsVisitApplyEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsVisitApplyService.updateById(entity);
        }
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("人员报备申请表 -导出")
    @RequiresPermissions("bms:visitApply:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsVisitApplyDTO> list = bmsVisitApplyService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "人员报备信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsVisitApplyExcel.class);
    }

    @GetMapping("verifyExport")
    @ApiOperation("导出")
    @LogOperation("人员报备申请表 -审批导出")
    @RequiresPermissions("bms:visitApply:export")
    public void verifyExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsVisitApplyDTO> list = bmsVisitApplyService.selectVerifyExportList(params);

        ExcelUtils.exportExcelToTarget(response, "人员报备信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsVisitApplyExcel.class);
    }


    @GetMapping("recordExport")
    @ApiOperation("导出")
    @LogOperation("人员报备申请表 -审批导出")
    @RequiresPermissions("bms:visitApply:export")
    public void recordExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsVisitApplyDTO> list = bmsVisitApplyService.selectRecordExportList(params);

        ExcelUtils.exportExcelToTarget(response, "人员报备信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsVisitApplyExcel.class);
    }

}