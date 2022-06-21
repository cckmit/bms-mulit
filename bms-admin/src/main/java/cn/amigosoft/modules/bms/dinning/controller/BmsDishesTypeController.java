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
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesTypeDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesTypeEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsDishesTypeExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsDishesTypeService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜品类别表
 */
@RestController
@RequestMapping("bms/dishesType")
@Api(tags = "菜品类别表 ")
public class BmsDishesTypeController {
    @Autowired
    private BmsDishesTypeService bmsDishesTypeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("菜品类别管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:dishesType:page")
    public Result<PageData<BmsDishesTypeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsDishesTypeDTO> page = bmsDishesTypeService.page(params);

        return new Result<PageData<BmsDishesTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("菜品类别管理-详情")
    @RequiresPermissions("bms:dishesType:info")
    public Result<BmsDishesTypeDTO> get(@PathVariable("id") Long id) {
        BmsDishesTypeDTO data = bmsDishesTypeService.get(id);

        return new Result<BmsDishesTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("菜品类别管理-保存")
    @RequiresPermissions("bms:dishesType:save")
    public Result save(@RequestBody BmsDishesTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsDishesTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsDishesTypeService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该菜品类别名称已存在");
        }
        bmsDishesTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("菜品类别管理-修改")
    @RequiresPermissions("bms:dishesType:update")
    public Result update(@RequestBody BmsDishesTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsDishesTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsDishesTypeEntity data = bmsDishesTypeService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该菜品类别名称已存在");
        }
        bmsDishesTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("菜品类别管理-删除")
    @RequiresPermissions("bms:dishesType:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsDishesTypeEntity entity = new BmsDishesTypeEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsDishesTypeService.updateById(entity);
        }
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("菜品类别管理-导出")
    @RequiresPermissions("bms:dishesType:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsDishesTypeDTO> list = bmsDishesTypeService.list(params);

        ExcelUtils.exportExcelToTarget(response, "菜品类别信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsDishesTypeExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("菜品类别表-菜品类别列表")
    public Result<List<BmsDishesTypeDTO>> list() {
        Map<String, Object> params = new HashMap<>();
        params.put("del", BmsConstant.NOT_DEL);
        List<BmsDishesTypeDTO> list = bmsDishesTypeService.list(params);
        List<BmsDishesTypeDTO> result = new ArrayList<>();
        BmsDishesTypeDTO dishesTypeDTO = new BmsDishesTypeDTO();
        dishesTypeDTO.setId(BmsConstant.EMPTY_ID);
        dishesTypeDTO.setName(BmsConstant.EMPTY_NAME);
        result.add(dishesTypeDTO);
        if (list.size() > 0) {
            result.addAll(list);
        }
        return new Result<List<BmsDishesTypeDTO>>().ok(result);
    }

}