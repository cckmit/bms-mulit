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
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsItemExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 餐品表
 */
@RestController
@RequestMapping("bms/item")
@Api(tags = "餐品表 ")
public class BmsItemController {

    @Autowired
    private BmsItemService bmsItemService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("餐品管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<BmsItemDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsItemService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("餐品管理-详情")
    @RequiresPermissions("bms:item:info")
    public Result<BmsItemDTO> get(@PathVariable("id") Long id) {
        BmsItemDTO data = bmsItemService.selectItemById(id);

        return new Result<BmsItemDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("餐品管理-保存")
    @RequiresPermissions("bms:item:save")
    public Result save(@RequestBody BmsItemDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsItemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("canteen_id", dto.getCanteenId());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsItemService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该食堂已存在相同名称的餐品");
        }
        bmsItemService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("餐品管理-修改")
    @RequiresPermissions("bms:item:update")
    public Result update(@RequestBody BmsItemDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsItemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("canteen_id", dto.getCanteenId());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsItemEntity data = bmsItemService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该食堂已存在相同名称的餐品");
        }
        bmsItemService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("餐品管理-删除")
    @RequiresPermissions("bms:item:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsItemEntity entity = new BmsItemEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsItemService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("餐品管理-导出")
    @RequiresPermissions("bms:item:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsItemDTO> list = bmsItemService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "餐品信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsItemExcel.class);
    }

    @GetMapping("search/list")
    @ApiOperation("餐品搜索项")
    @LogOperation("报餐报表-餐品搜索项")
    public Result<List<BmsItemDTO>> itemList() {
        List<BmsItemDTO> list = bmsItemService.getItemList();
        return new Result<List<BmsItemDTO>>().ok(list);
    }
}