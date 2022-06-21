package cn.amigosoft.modules.assets.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.assets.dto.AssetsRepairDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairDetailDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairPageListDTO;
import cn.amigosoft.modules.assets.service.AssetsRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
* <p>
*  资产维修表 前端控制器
* </p>
*
* @author : hupsh
* @version : 1.0
* @date : 2021-05-31 11:00:31
*/
@Slf4j
@Api(tags={"资产维修接口"})
@RestController
@RequestMapping("/assets/assetsRepair")
public class AssetsRepairController {

    @Autowired
    private AssetsRepairService assetsRepairService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "assets_name", value = "资产名称", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "creator", value = "报修人", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "status", value = "当前进度", paramType = "query", dataType="int")
    })
    @RequiresPermissions("assets:assetsRepair:page")
    public Result<PageData<AssetsRepairPageListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AssetsRepairPageListDTO> page = assetsRepairService.getAssetsRepairPageList(params);

        return new Result<PageData<AssetsRepairPageListDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("assets:assetsRepair:info")
    public Result<AssetsRepairDetailDTO> get(@PathVariable("id") Long id){
        AssetsRepairDetailDTO data = assetsRepairService.getAssetsRepairDetail(id);

        return new Result<AssetsRepairDetailDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产维修表-保存")
    @RequiresPermissions("assets:assetsRepair:save")
    public Result save(@RequestBody AssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        assetsRepairService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产维修表-修改")
    @RequiresPermissions("assets:assetsRepair:update")
    public Result update(@RequestBody AssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        assetsRepairService.update(dto);

        return new Result();
    }

    @PutMapping("dealAssetsRepair")
    @ApiOperation("处理维修工单")
    @LogOperation("资产维修表-处理")
    @RequiresPermissions("assets:assetsRepair:dealAssetsRepair")
    public Result dealAssetsRepair(@RequestBody AssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        Result result =  assetsRepairService.dealAssetsRepair(dto);

        return result;
    }

    @PutMapping("evaluateAssetsRepair")
    @ApiOperation("评价维修工单")
    @LogOperation("资产维修表-评价")
    @RequiresPermissions("assets:assetsRepair:evaluateAssetsRepair")
    public Result evaluateAssetsRepair(@RequestBody AssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto,DefaultGroup.class);
        Result result = assetsRepairService.evaluateAssetsRepair(dto);

        return result;
    }
}
