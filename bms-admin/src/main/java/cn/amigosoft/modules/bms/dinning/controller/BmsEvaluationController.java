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
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationTreeDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationUserDTO;
import cn.amigosoft.modules.bms.dinning.excel.BmsEvaluationExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsEvaluationService;
import cn.amigosoft.modules.bms.dinning.service.BmsEvaluationUserService;
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
 * 评价表
 */
@RestController
@RequestMapping("bms/evaluation")
@Api(tags = "评价表 ")
public class BmsEvaluationController {

    @Autowired
    private BmsEvaluationService bmsEvaluationService;

    @Autowired
    private BmsEvaluationUserService bmsEvaluationUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("用餐评价管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:evaluation:page")
    public Result<PageData<BmsEvaluationDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsEvaluationService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("用餐评价管理-详情")
    @RequiresPermissions("bms:evaluation:info")
    public Result<BmsEvaluationDTO> get(@PathVariable("id") Long id) {
        BmsEvaluationDTO data = bmsEvaluationService.selectEvaluationById(id);

        return new Result<BmsEvaluationDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("用餐评价管理-保存")
    @RequiresPermissions("bms:evaluation:save")
    public Result save(@RequestBody BmsEvaluationDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        bmsEvaluationService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("用餐评价管理-修改")
    @RequiresPermissions("bms:evaluation:update")
    public Result update(@RequestBody BmsEvaluationDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsEvaluationService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("用餐评价管理-删除")
    @RequiresPermissions("bms:evaluation:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        bmsEvaluationService.deleteEvaluationByIds(ids);
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("用餐评价管理-导出")
    @RequiresPermissions("bms:evaluation:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsEvaluationDTO> list = bmsEvaluationService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "评价信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsEvaluationExcel.class);
    }

    @GetMapping("user/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @LogOperation("用餐评价管理-评价内容分页")
    @RequiresPermissions("bms:evaluation:page")
    public Result<PageData<BmsEvaluationUserDTO>> userPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsEvaluationUserService.getPage(params);
    }

    @GetMapping("user/{id}")
    @ApiOperation("详情")
    @LogOperation("用餐评价管理-评价内容详情")
    @RequiresPermissions("bms:evaluation:info")
    public Result<BmsEvaluationUserDTO> userGet(@PathVariable("id") Long id) {
        BmsEvaluationUserDTO data = bmsEvaluationUserService.selectEvaluationDetail(id);
        return new Result<BmsEvaluationUserDTO>().ok(data);
    }

    @GetMapping("evaluationStatistics/{id}")
    @ApiOperation("评价统计")
    @LogOperation("用餐评价管理-评价统计")
    @RequiresPermissions("bms:evaluation:info")
    public Result<List<BmsEvaluationTreeDTO>> getEvaluationStatistics(@PathVariable("id") Long id) {
        List<BmsEvaluationTreeDTO> data = bmsEvaluationUserService.getEvaluationStatistics(id);
        return new Result<List<BmsEvaluationTreeDTO>>().ok(data);
    }

}