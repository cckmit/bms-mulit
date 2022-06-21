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
import cn.amigosoft.modules.assets.dto.AssetsRepairDealDTO;
import cn.amigosoft.modules.assets.excel.AssetsRepairDealExcel;
import cn.amigosoft.modules.assets.service.AssetsRepairDealService;
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
* <p>
*  资产维修处理表 前端控制器
* </p>
*
* @author : hupishi
* @version : 1.0
* @date : 2021-06-01 15:20:45
*/
@Api(tags={"资产维修处理表模块-资产维修处理表接口"})
@RestController
@RequestMapping("/assets/assetsRepairDeal")
public class AssetsRepairDealController {

    @Autowired
    private AssetsRepairDealService assetsRepairDealService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("assets:assetsRepairDeal:page")
    public Result<PageData<AssetsRepairDealDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AssetsRepairDealDTO> page = assetsRepairDealService.page(params);

        return new Result<PageData<AssetsRepairDealDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("assets:assetsRepairDeal:info")
    public Result<AssetsRepairDealDTO> get(@PathVariable("id") Long id){
        AssetsRepairDealDTO data = assetsRepairDealService.get(id);

        return new Result<AssetsRepairDealDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产维修处理表-保存")
    @RequiresPermissions("assets:assetsRepairDeal:save")
    public Result save(@RequestBody AssetsRepairDealDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        assetsRepairDealService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产维修处理表-修改")
    @RequiresPermissions("assets:assetsRepairDeal:update")
    public Result update(@RequestBody AssetsRepairDealDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        assetsRepairDealService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产维修处理表-删除")
    @RequiresPermissions("assets:assetsRepairDeal:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        assetsRepairDealService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("资产维修处理表-导出")
    @RequiresPermissions("assets:assetsRepairDeal:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AssetsRepairDealDTO> list = assetsRepairDealService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, AssetsRepairDealExcel.class);
    }

}
