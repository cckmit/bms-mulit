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
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsDTO;
import cn.amigosoft.modules.bms.repair.excel.BmsAssetsExcel;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsService;
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
 * 资产表
 */
@RestController
@RequestMapping("bms/assets")
@Api(tags = "资产表 ")
public class BmsAssetsController {
    @Autowired
    private BmsAssetsService bmsAssetsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("资产信息-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assets:page")
    public Result<PageData<BmsAssetsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("资产信息-详情")
    @RequiresPermissions("bms:assets:info")
    public Result<BmsAssetsDTO> get(@PathVariable("id") Long id) {
        BmsAssetsDTO data = bmsAssetsService.selectAssetsById(id);

        return new Result<BmsAssetsDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产信息-保存")
    @RequiresPermissions("bms:assets:save")
    public Result save(@RequestBody BmsAssetsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产信息-修改")
    @RequiresPermissions("bms:assets:update")
    public Result update(@RequestBody BmsAssetsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产信息-删除")
    @RequiresPermissions("bms:assets:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsAssetsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("资产信息-导出")
    @RequiresPermissions("bms:assets:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsDTO> list = bmsAssetsService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "资产信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsExcel.class);
    }

}