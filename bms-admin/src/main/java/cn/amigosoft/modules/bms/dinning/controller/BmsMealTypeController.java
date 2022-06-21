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
import cn.amigosoft.modules.bms.dinning.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsMealTypeExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsMealTypeService;
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
 * 餐别表
 */
@RestController
@RequestMapping("bms/mealType")
@Api(tags = "餐别表 ")
public class BmsMealTypeController {
    @Autowired
    private BmsMealTypeService bmsMealTypeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("餐别管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:mealType:page")
    public Result<PageData<BmsMealTypeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsMealTypeDTO> page = bmsMealTypeService.page(params);

        return new Result<PageData<BmsMealTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("餐别管理-详情")
    @RequiresPermissions("bms:mealType:info")
    public Result<BmsMealTypeDTO> get(@PathVariable("id") Long id) {
        BmsMealTypeDTO data = bmsMealTypeService.get(id);

        return new Result<BmsMealTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("餐别管理-保存")
    @RequiresPermissions("bms:mealType:save")
    public Result save(@RequestBody BmsMealTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsMealTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsMealTypeService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该餐别名称已存在");
        }
        bmsMealTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("餐别管理-修改")
    @RequiresPermissions("bms:mealType:update")
    public Result update(@RequestBody BmsMealTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsMealTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsMealTypeEntity data = bmsMealTypeService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该餐别名称已存在");
        }
        bmsMealTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("餐别管理-删除")
    @RequiresPermissions("bms:mealType:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsMealTypeEntity entity = new BmsMealTypeEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsMealTypeService.updateById(entity);
        }
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("餐别管理-导出")
    @RequiresPermissions("bms:mealType:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsMealTypeDTO> list = bmsMealTypeService.list(params);

        ExcelUtils.exportExcelToTarget(response, "餐别信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsMealTypeExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("餐别表-信息列表")
    public Result<List<BmsMealTypeDTO>> list() {
        Map<String, Object> params = new HashMap<>();
        params.put("del", BmsConstant.NOT_DEL);
        List<BmsMealTypeDTO> list = bmsMealTypeService.list(params);
        List<BmsMealTypeDTO> result = new ArrayList<>();
        BmsMealTypeDTO mealTypeDTO = new BmsMealTypeDTO();
        mealTypeDTO.setId(BmsConstant.EMPTY_ID);
        mealTypeDTO.setName(BmsConstant.EMPTY_NAME);
        result.add(mealTypeDTO);
        if (list.size() > 0) {
            result.addAll(list);
        }
        return new Result<List<BmsMealTypeDTO>>().ok(result);
    }
}
