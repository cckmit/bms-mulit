package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsVerificationStatisticsDTO;
import cn.amigosoft.modules.bms.dinning.excel.BmsVerificationStatisticsExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsVerificationStatisticsService;
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
 * 核销统计表
 */
@RestController
@RequestMapping("bms/verificationStatistics")
@Api(tags = "核销统计表 ")
public class BmsVerificationStatisticsController {

    @Autowired
    private BmsVerificationStatisticsService bmsVerificationStatisticsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @LogOperation("核销统计-分页")
    @RequiresPermissions("bms:verificationStatistics:page")
    public Result<PageData<BmsVerificationStatisticsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsVerificationStatisticsService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:verificationStatistics:info")
    public Result<BmsVerificationStatisticsDTO> get(@PathVariable("id") Long id) {
        BmsVerificationStatisticsDTO data = bmsVerificationStatisticsService.get(id);

        return new Result<BmsVerificationStatisticsDTO>().ok(data);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("核销统计-导出")
    @RequiresPermissions("bms:verificationStatistics:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsVerificationStatisticsDTO> list = bmsVerificationStatisticsService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "核销统计信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsVerificationStatisticsExcel.class);
    }

}