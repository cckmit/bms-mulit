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
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailInfoDTO;
import cn.amigosoft.modules.dining.service.BmsOrderDetailService;
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
 * 订单详情表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@RestController
@RequestMapping("dining/orderDetail")
@Api(tags = "订单详情表 ")
public class BmsOrderDetailController {
    @Autowired
    private BmsOrderDetailService bmsOrderDetailService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    //@RequiresPermissions("activity:bmsorderdetail:page")
    public Result<PageData<BmsOrderDetailDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsOrderDetailDTO> page = bmsOrderDetailService.page(params);

        return new Result<PageData<BmsOrderDetailDTO>>().ok(page);
    }

    @GetMapping("paidPage")
    @ApiOperation("核销分页")
    @LogOperation("小程序-用餐核销-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    //@RequiresPermissions("activity:bmsorderdetail:page")
    public Result<PageData<BmsOrderDetailDTO>> paidPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsOrderDetailDTO> page = bmsOrderDetailService.paidPage(params);

        return new Result<PageData<BmsOrderDetailDTO>>().ok(page);
    }

    @GetMapping("scanPaidPage")
    @ApiOperation("扫码核销记录分页")
    @LogOperation("小程序-扫码核销记录-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<BmsOrderDetailDTO>> scanPaidPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsOrderDetailDTO> page = bmsOrderDetailService.scanPaidPage(params);

        return new Result<PageData<BmsOrderDetailDTO>>().ok(page);
    }

    @GetMapping("countPage")
    @ApiOperation("报餐人数分页")
    @LogOperation("小程序-报餐人数-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<BmsOrderDetailDTO>> countPage(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsOrderDetailDTO> page = bmsOrderDetailService.countPage(params);
        return new Result<PageData<BmsOrderDetailDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情集合")
    @LogOperation("小程序-报餐记录-订单详情")
    //@RequiresPermissions("activity:bmsorderdetail:info")
    public Result<List<BmsOrderDetailInfoDTO>> getDetailInfo(@PathVariable("id") Long id) {
        List<BmsOrderDetailInfoDTO> data = bmsOrderDetailService.getDetailInfo(id);

        return new Result<List<BmsOrderDetailInfoDTO>>().ok(data);
    }

    @GetMapping("paidDetail/{id}")
    @ApiOperation("核销详情")
    @LogOperation("小程序-用餐核销-详情")
    //@RequiresPermissions("activity:bmsorderdetail:info")
    public Result<BmsOrderDetailDTO> getPaidDetailInfo(@PathVariable("id") Long id) {
        BmsOrderDetailDTO dto = bmsOrderDetailService.getPaidDetailInfo(id);
        return new Result<BmsOrderDetailDTO>().ok(dto);
    }

    @GetMapping("scanDetail")
    @ApiOperation("扫码详情")
    @LogOperation("小程序-扫码核销-详情")
    public Result<BmsOrderDetailDTO> getScanDetailInfo(@ApiIgnore @RequestParam Map<String, Object> params) {
        BmsOrderDetailDTO dto = bmsOrderDetailService.getScanDetailInfo(params);
        return new Result<BmsOrderDetailDTO>().ok(dto);
    }

    @PostMapping
    @ApiOperation("保存")
    //@LogOperation("订单详情表 -保存")
    //@RequiresPermissions("activity:bmsorderdetail:save")
    public Result save(@RequestBody BmsOrderDetailDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsOrderDetailService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    //@LogOperation("订单详情表 -修改")
    //@RequiresPermissions("activity:bmsorderdetail:update")
    public Result update(@RequestBody BmsOrderDetailDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsOrderDetailService.update(dto);

        return new Result();
    }

    @GetMapping("delete/{Id}")
    @ApiOperation("删除")
    @LogOperation("小程序-订单详情-取消")
    //@RequiresPermissions("activity:bmsorderdetail:delete")
    public Result delete(@PathVariable("Id") Long id) {
        //效验数据

        Result result = bmsOrderDetailService.delete(id);

        return result;
        //return new Result();
    }

    @GetMapping("onOrderItemIds")
    @ApiOperation("已订购")
    public Result<List<Long>> onOrderItemIds() {
        List<Long> list = bmsOrderDetailService.onOrderItemIds();
        return new Result<List<Long>>().ok(list);
    }

    @GetMapping("lastCanteen")
    @ApiOperation("订单-上一单食堂")
    public Result<BmsOrderDetailDTO> lastCanteen() {
        BmsOrderDetailDTO dto = bmsOrderDetailService.lastCanteen();
        return new Result<BmsOrderDetailDTO>().ok(dto);
    }
}