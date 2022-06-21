package cn.amigosoft.modules.bms.other.controller;

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
import cn.amigosoft.modules.bms.other.dto.BmsGoodsDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsEntity;
import cn.amigosoft.modules.bms.other.excel.BmsGoodsExcel;
import cn.amigosoft.modules.bms.other.service.BmsGoodsService;
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
 * 货品表
 */
@RestController
@RequestMapping("bms/goods")
@Api(tags = "货品表 ")
public class BmsGoodsController {
    @Autowired
    private BmsGoodsService bmsGoodsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:goods:page")
    public Result<PageData<BmsGoodsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsGoodsService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:goods:info")
    public Result<BmsGoodsDTO> get(@PathVariable("id") Long id) {
        BmsGoodsDTO data = bmsGoodsService.selectGoodsById(id);

        return new Result<BmsGoodsDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("货品表 -保存")
    @RequiresPermissions("bms:goods:save")
    public Result save(@RequestBody BmsGoodsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsGoodsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("货品表 -修改")
    @RequiresPermissions("bms:goods:update")
    public Result update(@RequestBody BmsGoodsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsGoodsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("货品表 -删除")
    @RequiresPermissions("bms:goods:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsGoodsEntity entity = new BmsGoodsEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsGoodsService.updateById(entity);
        }
        bmsGoodsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("货品表 -导出")
    @RequiresPermissions("bms:goods:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsGoodsDTO> list = bmsGoodsService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "货品信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsGoodsExcel.class);
    }

}