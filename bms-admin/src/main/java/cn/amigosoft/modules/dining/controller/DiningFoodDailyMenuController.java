package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.dining.dto.DiningFoodAddDailyMenuDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuListDTO;
import cn.amigosoft.modules.dining.service.DiningFoodDailyMenuService;
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
*  每日菜品 前端控制器
* </p>
*
* @author : 陈耀
* @version : 1.0
* @date : 2021-04-20 14:45:58
*/
@Api(tags={"每日菜品模块-每日菜品接口"})
@RestController
@RequestMapping("/dining/diningFoodDailyMenu")
public class DiningFoodDailyMenuController {


    @Autowired
    private DiningFoodDailyMenuService diningFoodDailyMenuService;

    @GetMapping("page")
    @ApiOperation("每日菜品-分页")
    @LogOperation("每日菜品-展示-分页")
    @RequiresPermissions("dining:diningFoodDailyMenu:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = "date", value = "日期", paramType = "query",required = true, dataType="date"),
            @ApiImplicitParam(name = "diningType", value = "每日菜品时段 1.早餐 2.午餐 3.晚餐", paramType = "query",required = true, dataType="int"),
            @ApiImplicitParam(name = "diningRoomId", value = "餐厅ID", paramType = "query",required = true, dataType = "String"),
    })
    public Result<PageData<DiningFoodDailyMenuListDTO>> get(@ApiIgnore @RequestParam Map<String, Object> params) {

        PageData<DiningFoodDailyMenuListDTO> page = diningFoodDailyMenuService.getDailyData(params);

        return new Result<PageData<DiningFoodDailyMenuListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("每日菜品移除")
    @LogOperation("每日菜品-移除")
    @RequiresPermissions("dining:diningFoodDailyMenu:delete")
    public Result removeDailyMenu(@RequestBody Long[] ids) {
        //传入iotsaas_dining_food_daily_menu.id
        AssertUtils.isArrayEmpty(ids, "id");

        diningFoodDailyMenuService.removeDailyMenu(ids);

        return new Result();
    }
    @PostMapping("addDailyMenu")
    @ApiOperation("每日菜品添加")
    @LogOperation("每日菜品-添加")
    @RequiresPermissions("dining:diningFoodDailyMenu:save")
    public Result addDailyMenu(@RequestBody DiningFoodAddDailyMenuDTO dto) {

        ValidatorUtils.validateEntity(dto,  DefaultGroup.class);

        diningFoodDailyMenuService.addDailyMenu(dto);

        return new Result();
    }


}
