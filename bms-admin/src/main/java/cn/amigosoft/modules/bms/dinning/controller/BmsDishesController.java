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
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsDishesExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsDishesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 菜品表
 */
@RestController
@RequestMapping("bms/dishes")
@Api(tags = "菜品表 ")
public class BmsDishesController {
    @Autowired
    private BmsDishesService bmsDishesService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("菜品管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:dishes:page")
    public Result<PageData<BmsDishesDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsDishesService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("菜品管理-详情")
    @RequiresPermissions("bms:dishes:info")
    public Result<BmsDishesDTO> get(@PathVariable("id") Long id) {
        BmsDishesDTO data = bmsDishesService.selectDishes(id);

        return new Result<BmsDishesDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("菜品管理-保存")
    @RequiresPermissions("bms:dishes:save")
    public Result save(@RequestBody BmsDishesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsDishesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("canteen_id", dto.getCanteenId());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsDishesService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该食堂已存在相同名称的菜品");
        }
        bmsDishesService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("菜品管理-修改")
    @RequiresPermissions("bms:dishes:update")
    public Result update(@RequestBody BmsDishesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsDishesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("canteen_id", dto.getCanteenId());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsDishesEntity data = bmsDishesService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该食堂已存在相同名称的菜品");
        }
        bmsDishesService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("菜品管理-删除")
    @RequiresPermissions("bms:dishes:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsDishesEntity entity = new BmsDishesEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsDishesService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("菜品管理-导出")
    @RequiresPermissions("bms:dishes:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsDishesDTO> list = bmsDishesService.export(params);

        ExcelUtils.exportExcelToTarget(response, "菜品信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsDishesExcel.class);
    }

    @GetMapping("dishesTree")
    @ApiOperation("菜品列表")
    public Result<List<BmsDishesDTO>> dishesTree(BmsDishesDTO dto) {
        List<BmsDishesDTO> list = bmsDishesService.selectBaseDishesInfo(dto);
        return new Result<List<BmsDishesDTO>>().ok(list);
    }
}