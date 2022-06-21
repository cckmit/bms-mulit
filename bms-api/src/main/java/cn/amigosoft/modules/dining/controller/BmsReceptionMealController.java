package cn.amigosoft.modules.dining.controller;

//import cn.amigosoft.common.annotation.LogOperation;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.dining.service.BmsReceptionMealService;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * 接待餐表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@RestController
@RequestMapping("dining/reception")
@Api(tags="接待餐表 ")
public class BmsReceptionMealController {
    @Autowired
    private BmsReceptionMealService bmsReceptionMealService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-接待餐记录-列表")
    public Result<PageData<BmsReceptionMealDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsReceptionMealDTO> page = bmsReceptionMealService.queryPage(params);

        return new Result<PageData<BmsReceptionMealDTO>>().ok(page);
    }

    @GetMapping("verifyPage")
    @ApiOperation("分页")
    @LogOperation("小程序-接待餐审批-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BmsReceptionMealDTO>> verifyPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsReceptionMealDTO> page = bmsReceptionMealService.queryVerifyPage(params);

        return new Result<PageData<BmsReceptionMealDTO>>().ok(page);
    }

    @GetMapping("checkPage")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-接待餐查看-分页")
    public Result<PageData<BmsReceptionMealDTO>> checkPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsReceptionMealDTO> page = bmsReceptionMealService.queryCheckPage(params);

        return new Result<PageData<BmsReceptionMealDTO>>().ok(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("信息")
    @LogOperation("小程序-接待餐-详情")
    public Result<BmsReceptionMealDTO> get(@PathVariable("id") Long id){
        BmsReceptionMealDTO data = bmsReceptionMealService.getDetail(id);

        return new Result<BmsReceptionMealDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-接待餐申请-保存")
    public Result save(@RequestBody BmsReceptionMealDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        //bmsReceptionMealService.save(dto);
        bmsReceptionMealService.insertReception(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody BmsReceptionMealDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsReceptionMealService.update(dto);

        return new Result();
    }

    @GetMapping("delete/{id}")
    @ApiOperation("删除")
    @LogOperation("小程序-接待餐审批-删除")
    public Result delete(@PathVariable("id") Long id){
        Result result = bmsReceptionMealService.deleteReception(id);
        return result;
    }

    @GetMapping("canteenList")
    @ApiOperation("食堂")
    public Result getCanteenList() {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.getCanteenList();
        return new Result<List<BmsReceptionMealDTO>>().ok(list);
    }

    @GetMapping("mealTypeList")
    @ApiOperation("餐别")
    public Result getMealTypeList() {
        List<BmsReceptionMealDTO> list = bmsReceptionMealService.getMealTypeList();
        return new Result<List<BmsReceptionMealDTO>>().ok(list);
    }

    @GetMapping("managerVerifyList")
    @ApiOperation("主管审批人员")
    public Result getManagerVerifyList() {
        List<SysUserDTO> list = bmsReceptionMealService.getManagerVerifyList();
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("officeVerifyList")
    @ApiOperation("办公室审批人员")
    public Result getOfficeVerifyList() {
        List<SysUserDTO> list = bmsReceptionMealService.getOfficeVerifyList();
        return new Result<List<SysUserDTO>>().ok(list);
    }

}