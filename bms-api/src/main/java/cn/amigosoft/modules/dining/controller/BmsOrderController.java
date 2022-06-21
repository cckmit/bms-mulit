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
import cn.amigosoft.modules.dining.dto.BmsEatDateDTO;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDTO;
import cn.amigosoft.modules.dining.service.BmsOrderService;
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
 * 订单表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@RestController
@RequestMapping("dining/order")
@Api(tags="订单表 ")
public class BmsOrderController {
    @Autowired
    private BmsOrderService bmsOrderService;

    @GetMapping("page")
    @ApiOperation("报餐订单-列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-报餐记录-列表")
    public Result<PageData<BmsOrderDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsOrderDTO> page = bmsOrderService.queryPage(params);

        return new Result<PageData<BmsOrderDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("基本信息")
    @LogOperation("小程序-报餐记录-详情")
    public Result<BmsOrderDTO> getBaseInfo(@PathVariable("id") Long id){
        BmsOrderDTO data = bmsOrderService.getBaseInfo(id);

        return new Result<BmsOrderDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-我要报餐-提交")
    public Result save(@RequestBody BmsOrderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsOrderService.insertOrder(dto);
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody BmsOrderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsOrderService.update(dto);

        return new Result();
    }

    @GetMapping("delete/{Id}")
    @ApiOperation("报餐订单-删除")
    @LogOperation("小程序-报餐记录-删除")
    public Result delete(@PathVariable("Id") Long id){

        Result result = bmsOrderService.delete(id);
        return result;
    }

    @PostMapping("chooseDates")
    @ApiOperation("报餐订单-用餐可选日期")
    public Result<List<BmsEatDateDTO>> chooseEatDates(@RequestBody BmsMealTypeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        List<BmsEatDateDTO> bmsEatDateDTOList = bmsOrderService.chooseEatDates(dto);
        return new Result<List<BmsEatDateDTO>>().ok(bmsEatDateDTOList);
    }

}