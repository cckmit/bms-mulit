package cn.amigosoft.modules.dining.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.ImportUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodLibEntity;
import cn.amigosoft.modules.dining.excel.DiningFoodLibExcel;
import cn.amigosoft.modules.dining.service.DiningFoodDailyMenuService;
import cn.amigosoft.modules.dining.service.DiningFoodLibService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* <p>
*  菜品库表 前端控制器
* </p>
*
* @author : hupihshi
* @version : 1.0
* @date : 2021-04-20 14:31:04
*/
@Api(tags={"菜品库表模块-菜品库表接口"})
@RestController
@RequestMapping("/dining/diningFoodLib")
public class DiningFoodLibController {


    @Autowired
    private DiningFoodLibService diningFoodLibService;


    @Autowired
    private DiningFoodDailyMenuService diningFoodDailyMenuService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "name", value = "菜品名称", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "libNo", value = "菜品编号", paramType = "query", dataType="String")
    })
    @RequiresPermissions("dining:diningFoodLib:page")
    public Result<PageData<DiningFoodLibPageDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<DiningFoodLibPageDTO> page = diningFoodLibService.getDiningFoodLibPage(params);

        return new Result<PageData<DiningFoodLibPageDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("dining:diningFoodLib:info")
    public Result<DiningFoodLibDTO> get(@PathVariable("id") Long id){
        DiningFoodLibDTO data = diningFoodLibService.get(id);

        return new Result<DiningFoodLibDTO>().ok(data);
}

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("菜品库表-保存")
    @RequiresPermissions("dining:diningFoodLib:save")
    public Result save(@RequestBody DiningFoodLibDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto);
        Result res = diningFoodLibService.validateDtoData(dto);
        if(res.getCode() != 0){
            return new Result().error(res.getMsg());
        }

        //生成菜品编号
         dto =  diningFoodLibService.getFoodLibNo(dto);

        //设置默认值
        dto.setIsEnduring(1);
        Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
        String dayInWeek = todayWeek.get("dayInWeek");
        dto.setWeek(dayInWeek);

        //插入数据时的前置处理
        diningFoodLibService.save(dto);

        //将周期菜品新增到菜单
        diningFoodDailyMenuService.insertDingFoodLibMenu(dto,2);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("菜品库表-修改")
    @RequiresPermissions("dining:diningFoodLib:update")
    public Result update(@RequestBody DiningFoodLibDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto);
        Result res = diningFoodLibService.validateDtoData(dto);
        if(res.getCode() != 0){
            return new Result().error(res.getMsg());
        }

        //设置默认值
        dto.setIsEnduring(1);
        Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
        String dayInWeek = (String) todayWeek.get("dayInWeek");
        dto.setWeek(dayInWeek);

//        if(dto.getIsEnduring() == 2){
//            dto.setWeek("");
//            dto.setDiningType("");
//        }

        if(dto.getRemark() == ""){
            dto.setRemark("");
        }

        diningFoodLibService.update(dto);
        if(dto.getType() == null || dto.getPrice() == null){
            UpdateWrapper updateWrapper =   new UpdateWrapper<DiningFoodLibEntity>().eq("id",dto.getId());

            if(dto.getType() == null){
                updateWrapper.set("type",null);
            }
            if(dto.getPrice() == null){
                updateWrapper.set("price",null);
            }

            diningFoodLibService.update(null,updateWrapper);
        }

        //将周期菜品新增到菜单
        diningFoodDailyMenuService.insertDingFoodLibMenu(dto,1);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("菜品库表-删除")
    @RequiresPermissions("dining:diningFoodLib:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        //删除菜品信息
        diningFoodLibService.setFoodLibIsDelete(ids);

        //删除每日菜单信息
        diningFoodDailyMenuService.deleteDingFoodLibMenuByDiningFoodLibIds(ids,2);
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("菜品库表-导出")
    @RequiresPermissions("dining:diningFoodLib:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DiningFoodLibDTO> list = diningFoodLibService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, DiningFoodLibExcel.class);
    }

    @GetMapping("addDailyMenuList")
    @ApiOperation("每日菜品添加分页")
    @LogOperation("每日菜品-添加-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = "date", value = "日期", paramType = "query",required = true, dataType="date"),
            @ApiImplicitParam(name = "diningType", value = "每日菜品时段 1.早餐 2.午餐 3.晚餐", paramType = "query",required = true, dataType="int"),
            @ApiImplicitParam(name = "name", value = "菜品名称", paramType = "query",required = false, dataType="string"),
            @ApiImplicitParam(name = "diningRoomId", value = "餐厅ID", paramType = "query",required = true, dataType = "String"),
    })
    @RequiresPermissions("dining:diningFoodLib:page")
    public Result<PageData<DiningFoodLibDTO>> addDailyMenuList(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<DiningFoodLibDTO> page = diningFoodLibService.addDailyMenuList(params);

        return new Result<PageData<DiningFoodLibDTO>>().ok(page);
    }

    @PostMapping("import")
    @ApiOperation("导入")
    @LogOperation("菜品库表-导入")
    @RequiresPermissions("dining:diningFoodLib:import")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        try {
            ImportParams params = new ImportParams();

            params.setHeadRows(1);
            List<DiningFoodLibExcel> diningFoodLibExcelList = ExcelImportUtil.importExcel(file.getInputStream(), DiningFoodLibExcel.class, params);
            if (diningFoodLibExcelList == null || diningFoodLibExcelList.size() < 1) {
                ImportUtils.noDataImport(response);
                return;
            }

            //数据校验，通过数据插入，不通过返回错误数据
            List<DiningFoodLibExcel> errorDiningFoodLibExcelList = new ArrayList<DiningFoodLibExcel>();
            diningFoodLibService.checkDiningFoodLibExcel(diningFoodLibExcelList, errorDiningFoodLibExcelList);

            if (errorDiningFoodLibExcelList.size() > 0) {
                String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
                String fileName = "菜品导入错误信息-" + dataStr;
                ImportUtils.errorImport(diningFoodLibExcelList.size(), response, errorDiningFoodLibExcelList, DiningFoodLibExcel.class, fileName);
            } else {
                ImportUtils.successImport(diningFoodLibExcelList.size(), response);
            }
        } catch (Exception e) {
            ImportUtils.errorParseExcel(response);
            return;
        }
    }


    @GetMapping("cronInsertDingFoodLibMenu")
    @ApiOperation("定时任务")
    public Result<Integer> cronInsertDingFoodLibMenu() {
         diningFoodLibService.cronInsertDingFoodLibMenu();
        return new Result();
    }
}
