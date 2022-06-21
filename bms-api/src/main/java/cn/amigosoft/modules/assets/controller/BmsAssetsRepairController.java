package cn.amigosoft.modules.assets.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.assets.service.BmsAssetsRepairService;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 资产维修表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@RestController
@RequestMapping("bms/assetsRepair")
@Api(tags="资产维修表 ")
public class BmsAssetsRepairController {
    @Autowired
    private BmsAssetsRepairService bmsAssetsRepairService;

    @GetMapping("page")
    @ApiOperation("报修分页")
    @LogOperation("小程序-报修记录-列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("activity:bmsassetsrepair:page")
    public Result<PageData<BmsAssetsRepairDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsRepairDTO> page = bmsAssetsRepairService.queryPage(params);

        return new Result<PageData<BmsAssetsRepairDTO>>().ok(page);
    }

    @GetMapping("verifyPage")
    @ApiOperation("审批分页")
    @LogOperation("小程序-报修审批-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BmsAssetsRepairDTO>> verifyPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsRepairDTO> page = bmsAssetsRepairService.queryVerifyPage(params);

        return new Result<PageData<BmsAssetsRepairDTO>>().ok(page);
    }

    @GetMapping("dealPage")
    @ApiOperation("处理分页")
    @LogOperation("小程序-维修管理-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BmsAssetsRepairDTO>> dealPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsRepairDTO> page = bmsAssetsRepairService.queryDealPage(params);

        return new Result<PageData<BmsAssetsRepairDTO>>().ok(page);
    }

    @GetMapping("servicePage")
    @ApiOperation("维修分页")
    @LogOperation("小程序-我来维修-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BmsAssetsRepairDTO>> servicePage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsRepairDTO> page = bmsAssetsRepairService.queryServicePage(params);

        return new Result<PageData<BmsAssetsRepairDTO>>().ok(page);
    }

    @GetMapping("detail")
    @ApiOperation("报修详情")
    @LogOperation("小程序-报修功能-详情")
    //@RequiresPermissions("activity:bmsassetsrepair:info")
    public Result<BmsAssetsRepairDTO> getDetail(Long id){
        BmsAssetsRepairDTO data = bmsAssetsRepairService.getDetail(id);

        return new Result<BmsAssetsRepairDTO>().ok(data);
    }

    @GetMapping("serviceDetail")
    @ApiOperation("维修详情")
    @LogOperation("小程序-我来维修-详情")
    //@RequiresPermissions("activity:bmsassetsrepair:info")
    public Result<BmsAssetsRepairDTO> getServiceDetail(Long id){
        BmsAssetsRepairDTO data = bmsAssetsRepairService.getServiceDetail(id);

        return new Result<BmsAssetsRepairDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("报修申请")
    @LogOperation("小程序-我要报修-保存")
    //@RequiresPermissions("activity:bmsassetsrepair:save")
    public Result save(@RequestBody BmsAssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsRepairService.addRepairApply(dto);
        //bmsAssetsRepairService.save(dto);

        return new Result();
    }

    @PostMapping("serviceResult")
    @ApiOperation("维修结果")
    @LogOperation("小程序-我来维修-保存")
    public Result serviceResult(@RequestBody BmsAssetsRepairDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        Result result = bmsAssetsRepairService.serviceResult(dto);
        return result;
    }

    @PostMapping("evaluation")
    @ApiOperation("评价")
    @LogOperation("小程序-报修记录-评价")
    //@RequiresPermissions("activity:bmsassetsrepair:save")
    public Result evaluation(@RequestBody BmsAssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsRepairService.evaluation(dto);
        //bmsAssetsRepairService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产维修表 -修改")
    @RequiresPermissions("activity:bmsassetsrepair:update")
    public Result update(@RequestBody BmsAssetsRepairDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsRepairService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产维修表 -删除")
    @RequiresPermissions("activity:bmsassetsrepair:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsAssetsRepairService.delete(ids);

        return new Result();
    }

    @GetMapping("verifyList")
    @ApiOperation("审批人员")
    //@LogOperation("资产维修表-审批人员")
    public Result<List<SysUserDTO>> getVerifyList() {
        List<SysUserDTO> list = bmsAssetsRepairService.getVerifyList();
        return new Result<List<SysUserDTO>>().ok(list);
    }
}