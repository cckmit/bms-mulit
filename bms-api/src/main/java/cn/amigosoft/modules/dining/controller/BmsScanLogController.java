package cn.amigosoft.modules.dining.controller;

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
import cn.amigosoft.modules.dining.dto.BmsScanLogDTO;
import cn.amigosoft.modules.dining.service.BmsScanLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * 扫码记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@RestController
@RequestMapping("dining/scanLog")
@Api(tags="扫码记录表")
public class BmsScanLogController {
    @Autowired
    private BmsScanLogService bmsScanLogService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BmsScanLogDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsScanLogDTO> page = bmsScanLogService.page(params);

        return new Result<PageData<BmsScanLogDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<BmsScanLogDTO> get(@PathVariable("id") Long id){
        BmsScanLogDTO data = bmsScanLogService.get(id);

        return new Result<BmsScanLogDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-扫码记录-保存")
    public Result<BmsScanLogDTO>  save(@RequestBody BmsScanLogDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        BmsScanLogDTO data = bmsScanLogService.addScanLog(dto);
        //bmsScanLogService.save(dto);

        return new Result<BmsScanLogDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("扫码记录表-修改")
    public Result update(@RequestBody BmsScanLogDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsScanLogService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("扫码记录表-删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsScanLogService.delete(ids);

        return new Result();
    }

}