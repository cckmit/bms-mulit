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
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.visit.service.BmsVisitApplyVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 人员报备审批表 
 */
@RestController
@RequestMapping("bms/visitApplyVerify")
@Api(tags="人员报备审批表 ")
public class BmsVisitApplyVerifyController {
    @Autowired
    private BmsVisitApplyVerifyService bmsVisitApplyVerifyService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("bms:visitApplyVerify:page")
    public Result<PageData<BmsVisitApplyVerifyDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyVerifyDTO> page = bmsVisitApplyVerifyService.page(params);

        return new Result<PageData<BmsVisitApplyVerifyDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:visitApplyVerify:info")
    public Result<BmsVisitApplyVerifyDTO> get(@PathVariable("id") Long id){
        BmsVisitApplyVerifyDTO data = bmsVisitApplyVerifyService.get(id);

        return new Result<BmsVisitApplyVerifyDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-访客审批-保存")
    //@RequiresPermissions("bms:visitApplyVerify:save")
    public Result save(@RequestBody BmsVisitApplyVerifyDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsVisitApplyVerifyService.saveVerify(dto);

    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("小程序-访客审批-修改")
    @RequiresPermissions("bms:visitApplyVerify:update")
    public Result update(@RequestBody BmsVisitApplyVerifyDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsVisitApplyVerifyService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("小程序-访客审批-删除")
    @RequiresPermissions("bms:visitApplyVerify:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsVisitApplyVerifyService.delete(ids);

        return new Result();
    }

}