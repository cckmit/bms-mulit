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
import cn.amigosoft.modules.dining.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.dining.service.BmsScanVerificationService;
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
 * 扫码核销表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@RestController
@RequestMapping("dining/scanVerification")
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
    public Result<PageData<BmsScanVerificationDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsScanVerificationDTO> page = bmsScanVerificationService.page(params);

        return new Result<PageData<BmsScanVerificationDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<BmsScanVerificationDTO> get(@PathVariable("id") Long id){
        BmsScanVerificationDTO data = bmsScanVerificationService.get(id);

        return new Result<BmsScanVerificationDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-扫码核销-保存")
    public Result save(@RequestBody BmsScanVerificationDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        Result result = bmsScanVerificationService.addScanVerification(dto);
        //bmsScanVerificationService.save(dto);

        return result;
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("扫码核销表-修改")
    public Result update(@RequestBody BmsScanVerificationDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsScanVerificationService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("扫码核销表-删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsScanVerificationService.delete(ids);

        return new Result();
    }

}