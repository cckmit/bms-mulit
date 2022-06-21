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
import cn.amigosoft.modules.bms.dinning.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.bms.dinning.excel.BmsScanVerificationExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsScanVerificationService;
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
 * 扫码核销表
 */
@RestController
@RequestMapping("bms/scanVerification")
@Api(tags="扫码核销表")
public class BmsScanVerificationController {
    @Autowired
    private BmsScanVerificationService bmsScanVerificationService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("bms:scanVerification:page")
    public Result<PageData<BmsScanVerificationDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        return bmsScanVerificationService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:scanVerification:info")
    public Result<BmsScanVerificationDTO> get(@PathVariable("id") Long id){
        BmsScanVerificationDTO data = bmsScanVerificationService.get(id);

        return new Result<BmsScanVerificationDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("扫码核销表-保存")
    @RequiresPermissions("bms:scanVerification:save")
    public Result save(@RequestBody BmsScanVerificationDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsScanVerificationService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("扫码核销表-修改")
    @RequiresPermissions("bms:scanVerification:update")
    public Result update(@RequestBody BmsScanVerificationDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsScanVerificationService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("扫码核销表-删除")
    @RequiresPermissions("bms:scanVerification:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsScanVerificationService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("扫码核销表-导出")
    @RequiresPermissions("bms:scanVerification:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsScanVerificationDTO> list = bmsScanVerificationService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "扫码核销信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsScanVerificationExcel.class);
    }

}
