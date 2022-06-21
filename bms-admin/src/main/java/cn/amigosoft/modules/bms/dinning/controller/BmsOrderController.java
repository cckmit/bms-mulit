package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 订单表
 */
@RestController
@RequestMapping("bms/order")
@Api(tags = "订单表 ")
public class BmsOrderController {
    @Autowired
    private BmsOrderService bmsOrderService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("订单管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:order:page")
    public Result<PageData<BmsOrderDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsOrderService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("订单管理-详情")
    @RequiresPermissions("bms:order:info")
    public Result<BmsOrderDTO> get(@PathVariable("id") Long id) {
        BmsOrderDTO data = bmsOrderService.selectOrderById(id);

        return new Result<BmsOrderDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("订单管理-保存")
    @RequiresPermissions("bms:order:save")
    public Result save(@RequestBody BmsOrderDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsOrderService.insertOrder(dto);
    }

    @PutMapping("cancel")
    @ApiOperation("取消")
    @LogOperation("订单管理-取消订单")
    @RequiresPermissions("bms:order:update")
    public Result cancel(@RequestBody List<BmsOrderDetailDTO> list) {
        return bmsOrderService.cancel(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("订单管理-删除")
    @RequiresPermissions("bms:order:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        return bmsOrderService.deleteOrderByIds(ids);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("订单管理-导出")
    @RequiresPermissions("bms:order:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsOrderDTO> list = bmsOrderService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "订单信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsOrderExcel.class);
    }

}