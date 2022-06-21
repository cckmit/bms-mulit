package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordInfoDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordPageDTO;
import cn.amigosoft.modules.dining.service.DiningAppointmentRecordService;
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
 * <p>
 * 下午茶预约记录 前端控制器
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:36:11
 */
@Api(tags = {"下午茶预约记录模块-下午茶预约记录接口"})
@RestController
@RequestMapping("/dining/diningAppointmentRecord")
public class DiningAppointmentRecordController {


    @Autowired
    private DiningAppointmentRecordService diningAppointmentRecordService;

    @GetMapping("page")
    @ApiOperation("菜品预约记录分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "diningRoomId", value = "餐厅id", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "beginTime", value = "预约开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "预约结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 0:待取餐, 1:已完成, 2:已取消", paramType = "query", dataType = "int"),
    })
    @RequiresPermissions("dining:diningAppointmentRecord:page")
    public Result<PageData<DiningAppointmentRecordPageDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<DiningAppointmentRecordPageDTO> page = diningAppointmentRecordService.getPage(params);
        return new Result<PageData<DiningAppointmentRecordPageDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("菜品预约记录详细")
    @RequiresPermissions("dining:diningAppointmentRecord:info")
    public Result<DiningAppointmentRecordInfoDTO> get(@PathVariable("id") Long id) {
        DiningAppointmentRecordInfoDTO data = diningAppointmentRecordService.getInfo(id);
        return new Result<DiningAppointmentRecordInfoDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("下午茶预约记录-保存")
    @RequiresPermissions("dining:diningAppointmentRecord:save")
    public Result save(@RequestBody DiningAppointmentRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        diningAppointmentRecordService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("下午茶预约记录-修改")
    @RequiresPermissions("dining:diningAppointmentRecord:update")
    public Result update(@RequestBody DiningAppointmentRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        diningAppointmentRecordService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("下午茶预约记录-删除")
    @RequiresPermissions("dining:diningAppointmentRecord:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        diningAppointmentRecordService.delete(ids);

        return new Result();
    }

    @GetMapping("appointmentFinsh/{id}")
    @ApiOperation("取餐完成")
    @RequiresPermissions("dining:appointmentFinsh:mealCollectionCompleted")
    public Result appointmentFinsh(@PathVariable("id") Long id) {
        Result result = diningAppointmentRecordService.appointmentFinsh(id);
        return result;
    }


}
