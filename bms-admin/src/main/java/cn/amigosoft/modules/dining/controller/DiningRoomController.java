package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.dining.dto.DiningRoomDTO;
import cn.amigosoft.modules.dining.dto.DiningRoomPageRespDTO;
import cn.amigosoft.modules.dining.dto.SaveOrUpdateDiningRoomReqDTO;
import cn.amigosoft.modules.dining.service.DiningRoomService;
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
 * <p>
 * 智慧餐厅 前端控制器
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@Api(tags = {"智慧餐厅模块-智慧餐厅接口"})
@RestController
@RequestMapping("/dining/diningRoom")
public class DiningRoomController {


    @Autowired
    private DiningRoomService diningRoomService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "餐厅名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("dining:diningRoom:page")
    public Result<PageData<DiningRoomPageRespDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return diningRoomService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<DiningRoomDTO> get(@PathVariable("id") Long id) {
        DiningRoomDTO info = diningRoomService.info(id);
        return new Result<DiningRoomDTO>().ok(info);

    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("dining:diningRoom:save")
    public Result save(@RequestBody SaveOrUpdateDiningRoomReqDTO reqDTO) {
        //效验数据
        ValidatorUtils.validateEntity(reqDTO, DefaultGroup.class);

        return diningRoomService.saveOrUpdate(reqDTO, true);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("dining:diningRoom:update")
    public Result update(@RequestBody SaveOrUpdateDiningRoomReqDTO reqDTO) {
        //效验数据
        ValidatorUtils.validateEntity(reqDTO, UpdateGroup.class, DefaultGroup.class);

        return diningRoomService.saveOrUpdate(reqDTO, false);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("dining:diningRoom:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        return diningRoomService.del(ids);
    }

    @GetMapping("/list")
    @ApiOperation("餐厅列表")
    public Result<List<DiningRoomDTO>> selectList() {
        List<DiningRoomDTO> list = diningRoomService.selectList();
        return new Result<List<DiningRoomDTO>>().ok(list);
    }


}
