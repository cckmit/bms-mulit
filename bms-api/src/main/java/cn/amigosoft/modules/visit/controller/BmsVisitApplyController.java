package cn.amigosoft.modules.visit.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.visit.service.BmsVisitApplyService;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
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
 * 人员报备申请表 
 */
@RestController
@RequestMapping("bms/visitApply")
@Api(tags="人员报备申请表 ")
public class BmsVisitApplyController {
    @Autowired
    private BmsVisitApplyService bmsVisitApplyService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-访客记录-列表")
    //@RequiresPermissions("bms:visitApply:page")
    public Result<PageData<BmsVisitApplyDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyDTO> page = bmsVisitApplyService.queryPage(params);

        return new Result<PageData<BmsVisitApplyDTO>>().ok(page);
    }

    @GetMapping("draftPage")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-草稿记录-列表")
    //@RequiresPermissions("bms:visitApply:page")
    public Result<PageData<BmsVisitApplyDTO>> draftPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyDTO> page = bmsVisitApplyService.queryDraftPage(params);

        return new Result<PageData<BmsVisitApplyDTO>>().ok(page);
    }

    @GetMapping("verifyPage")
    @ApiOperation("分页")
    @LogOperation("小程序-访客审批-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("bms:visitApply:page")
    public Result<PageData<BmsVisitApplyDTO>> verifyPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyDTO> page = bmsVisitApplyService.queryVerifyPage(params);

        return new Result<PageData<BmsVisitApplyDTO>>().ok(page);
    }

    @GetMapping("appliedPage")
    @ApiOperation("分页")
    @LogOperation("小程序-访客查看-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("bms:visitApply:page")
    public Result<PageData<BmsVisitApplyDTO>> appliedPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyDTO> page = bmsVisitApplyService.queryAppliedPage(params);

        return new Result<PageData<BmsVisitApplyDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:visitApply:info")
    public Result<BmsVisitApplyDTO> get(@PathVariable("id") Long id){
        BmsVisitApplyDTO data = bmsVisitApplyService.get(id);

        return new Result<BmsVisitApplyDTO>().ok(data);
    }

    @GetMapping("detail")
    @ApiOperation("信息")
    @LogOperation("小程序-访客报备-详情")
    //@RequiresPermissions("bms:visitApply:info")
    public Result<BmsVisitApplyDTO> getDetail(Long id){
        BmsVisitApplyDTO data = bmsVisitApplyService.getDetail(id);

        return new Result<BmsVisitApplyDTO>().ok(data);
    }

    @GetMapping("draft/detail")
    @ApiOperation("信息")
    @LogOperation("小程序-访客报备-详情")
    //@RequiresPermissions("bms:visitApply:info")
    public Result<BmsVisitApplyDTO> getDraftDetail(Long id){
        BmsVisitApplyDTO data = bmsVisitApplyService.getDraftDetail(id);

        return new Result<BmsVisitApplyDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-访客报备-保存")
    //@RequiresPermissions("bms:visitApply:save")
    public Result save(@RequestBody BmsVisitApplyDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsVisitApplyService.addVisitApply(dto);
        //bmsVisitApplyService.save(dto);

        return new Result();
    }

    @PostMapping("register")
    @ApiOperation("保存")
    @LogOperation("小程序-访客登记-保存")
    public Result register(@RequestBody BmsVisitApplyDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsVisitApplyService.addVisitRegister(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("登记提交")
    @LogOperation("小程序-访客登记-提交")
    //@RequiresPermissions("bms:visitApply:update")
    public Result update(@RequestBody BmsVisitApplyDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        bmsVisitApplyService.approveRegister(dto);
        return new Result();
    }

    @GetMapping("register/delete")
    @ApiOperation("登记删除")
    @LogOperation("小程序-访客登记 -删除")
    public Result registerDelete(Long id){
        Result result = bmsVisitApplyService.deleteVisitRegister(id);
        return result;
    }

    @PostMapping("draft")
    @ApiOperation("保存")
    @LogOperation("小程序-访客报备-新增草稿")
    public Result draft(@RequestBody BmsVisitApplyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        bmsVisitApplyService.addVisitDraft(dto);
        return new Result();
    }

    @GetMapping("draft/delete")
    @ApiOperation("草稿删除")
    // @LogOperation("小程序-访客登记 -删除")
    public Result draftDelete(Long id){
        Result result = bmsVisitApplyService.deleteVisitDraft(id);
        return result;
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("人员报备申请表 -删除")
    @RequiresPermissions("bms:visitApply:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsVisitApplyService.delete(ids);

        return new Result();
    }

    @GetMapping("verifyList")
    @ApiOperation("主管审批人员")
    public Result<List<SysUserDTO>> getVerifyList() {
        List<SysUserDTO> list = bmsVisitApplyService.getVerifyList();
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("guardVerifyList")
    @ApiOperation("保安审批人员")
    public Result<List<SysUserDTO>> getGuardVerifyList() {

        List<SysUserDTO> list = bmsVisitApplyService.getGuardVerifyList();
        return new Result<List<SysUserDTO>>().ok(list);
    }
}