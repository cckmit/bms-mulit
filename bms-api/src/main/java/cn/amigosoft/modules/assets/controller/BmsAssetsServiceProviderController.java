package cn.amigosoft.modules.assets.controller;

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
import cn.amigosoft.modules.assets.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.assets.service.BmsAssetsServiceProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 服务商表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@RestController
@RequestMapping("bms/serviceProvider")
@Api(tags="服务商表 ")
public class BmsAssetsServiceProviderController {
    @Autowired
    private BmsAssetsServiceProviderService bmsAssetsServiceProviderService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("小程序-服务商-列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("activity:bmsassetsserviceprovider:page")
    public Result<PageData<BmsAssetsServiceProviderDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsServiceProviderDTO> page = bmsAssetsServiceProviderService.queryPage(params);

        return new Result<PageData<BmsAssetsServiceProviderDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("activity:bmsassetsserviceprovider:info")
    public Result<BmsAssetsServiceProviderDTO> get(@PathVariable("id") Long id){
        BmsAssetsServiceProviderDTO data = bmsAssetsServiceProviderService.get(id);

        return new Result<BmsAssetsServiceProviderDTO>().ok(data);
    }

    @GetMapping("detail")
    @ApiOperation("信息")
    @LogOperation("小程序-服务商-详情")
    //@RequiresPermissions("activity:bmsassetsserviceprovider:info")
    public Result<BmsAssetsServiceProviderDTO> getDetail(Long id){
        BmsAssetsServiceProviderDTO data = bmsAssetsServiceProviderService.getDetail(id);

        return new Result<BmsAssetsServiceProviderDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("服务商表 -保存")
    @RequiresPermissions("activity:bmsassetsserviceprovider:save")
    public Result save(@RequestBody BmsAssetsServiceProviderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsServiceProviderService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("服务商表 -修改")
    @RequiresPermissions("activity:bmsassetsserviceprovider:update")
    public Result update(@RequestBody BmsAssetsServiceProviderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsServiceProviderService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("服务商表 -删除")
    @RequiresPermissions("activity:bmsassetsserviceprovider:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsAssetsServiceProviderService.delete(ids);

        return new Result();
    }

}