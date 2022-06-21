package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetail3Excel;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetailExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsOrderDetailService;
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
 * 订单详情表
 */
@RestController
@RequestMapping("bms/orderDetail")
@Api(tags = "订单详情表 ")
public class BmsOrderDetailController {

    @Autowired
    private BmsOrderDetailService bmsOrderDetailService;

    @GetMapping("page")
    @ApiOperation("报餐统计分页")
    @LogOperation("报餐统计-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:orderDetail:page")
    public Result<PageData<BmsOrderDetailDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsOrderDetailService.getPage(params);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("报餐统计-导出")
    @RequiresPermissions("bms:orderDetail:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsOrderDetailDTO> list = bmsOrderDetailService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "报餐统计信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsOrderDetailExcel.class);
    }

    @GetMapping("count")
    @ApiOperation("订单详情表 - 当日餐别订购数")
    public Result<Object> count(@ApiIgnore @RequestParam Map<String, Object> params) {
        int count = bmsOrderDetailService.selectUserDetailCount(params);
        return new Result<>().ok(count);
    }

    @GetMapping("countPage")
    @ApiOperation("报餐人数分页")
    @LogOperation("报餐人数-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:orderDetail:countPage")
    public Result<PageData<BmsOrderDetailDTO>> countPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsOrderDetailService.getCountPage(params);
    }

    @GetMapping("countExport")
    @ApiOperation("导出")
    @LogOperation("报餐人数-导出")
    @RequiresPermissions("bms:orderDetail:countExport")
    public void countExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsOrderDetailDTO> list = bmsOrderDetailService.selectCountExportList(params);

        ExcelUtils.exportExcelToTarget(response, "报餐人数信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsOrderDetail3Excel.class);
    }

}