package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentPageDTO;
import cn.amigosoft.modules.dining.excel.DiningFoodCommentExcel;
import cn.amigosoft.modules.dining.service.DiningFoodCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
* <p>
*  每日菜品评价 前端控制器
* </p>
*
* @author : 陈耀
* @version : 1.0
* @date : 2021-04-20 15:11:09
*/
@Api(tags={"每日菜品评价模块-每日菜品评价接口"})
@RestController
@RequestMapping("/dining/diningFoodComment")
public class DiningFoodCommentController {


    @Autowired
    private DiningFoodCommentService diningFoodCommentService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = "startMenuDate", value = "菜品日期 开始时间YYYY-MM-DD", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endMenuData", value = "菜品日期  结束时间YYYY-MM-DD", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "name", value = "菜品名称", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "diningRoomId", value = "餐厅id", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("dining:diningFoodComment:page")
    public Result<PageData<DiningFoodCommentPageDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){

        PageData<DiningFoodCommentPageDTO> page = diningFoodCommentService.getCommentPage(params);

        return new Result<PageData<DiningFoodCommentPageDTO>>().ok(page);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("每日菜品评价-导出")
    @RequiresPermissions("dining:diningFoodComment:export")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startMenuDate", value = "菜品日期 开始时间YYYY-MM-DD", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endMenuData", value = "菜品日期  结束时间YYYY-MM-DD", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "菜品名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "diningRoomId", value = "餐厅id", paramType = "query", dataType = "String")
    })
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DiningFoodCommentPageDTO> list = diningFoodCommentService.getCommentList(params);

        ExcelUtils.exportExcelToTarget(response, "菜品评价", list, DiningFoodCommentExcel.class);
    }

//    @GetMapping("{id}")
//    @ApiOperation("信息")
//    @RequiresPermissions("dining:diningFoodComment:info")
//    public Result<DiningFoodCommentDTO> get(@PathVariable("id") Long id){
//        DiningFoodCommentDTO data = diningFoodCommentService.get(id);
//
//        return new Result<DiningFoodCommentDTO>().ok(data);
//    }
//
//    @PostMapping
//    @ApiOperation("保存")
//    @LogOperation("每日菜品评价-保存")
//    @RequiresPermissions("dining:diningFoodComment:save")
//    public Result save(@RequestBody DiningFoodCommentDTO dto){
//        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        diningFoodCommentService.save(dto);
//
//        return new Result();
//    }
//
//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("每日菜品评价-修改")
//    @RequiresPermissions("dining:diningFoodComment:update")
//    public Result update(@RequestBody DiningFoodCommentDTO dto){
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        diningFoodCommentService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("每日菜品评价-删除")
//    @RequiresPermissions("dining:diningFoodComment:delete")
//    public Result delete(@RequestBody Long[] ids){
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        diningFoodCommentService.delete(ids);
//
//        return new Result();
//    }



}
