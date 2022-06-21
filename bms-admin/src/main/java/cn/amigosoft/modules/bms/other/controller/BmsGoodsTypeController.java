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
import cn.amigosoft.modules.bms.other.dto.BmsGoodsTypeDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsTypeEntity;
import cn.amigosoft.modules.bms.other.excel.BmsGoodsTypeExcel;
import cn.amigosoft.modules.bms.other.service.BmsGoodsTypeService;
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
 * 货品类别表
 */
@RestController
@RequestMapping("bms/goodsType")
@Api(tags = "货品类别表 ")
public class BmsGoodsTypeController {
    @Autowired
    private BmsGoodsTypeService bmsGoodsTypeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:goodsType:page")
    public Result<PageData<BmsGoodsTypeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsGoodsTypeDTO> page = bmsGoodsTypeService.page(params);

        return new Result<PageData<BmsGoodsTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:goodsType:info")
    public Result<BmsGoodsTypeDTO> get(@PathVariable("id") Long id) {
        BmsGoodsTypeDTO data = bmsGoodsTypeService.get(id);

        return new Result<BmsGoodsTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("货品类别表 -保存")
    @RequiresPermissions("bms:goodsType:save")
    public Result save(@RequestBody BmsGoodsTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsGoodsTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("货品类别表 -修改")
    @RequiresPermissions("bms:goodsType:update")
    public Result update(@RequestBody BmsGoodsTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsGoodsTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("货品类别表 -删除")
    @RequiresPermissions("bms:goodsType:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsGoodsTypeEntity entity = new BmsGoodsTypeEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsGoodsTypeService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("货品类别表 -导出")
    @RequiresPermissions("bms:goodsType:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsGoodsTypeDTO> list = bmsGoodsTypeService.list(params);

        ExcelUtils.exportExcelToTarget(response, "货品类别信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsGoodsTypeExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    @LogOperation("货品类别表 -列表")
    @RequiresPermissions("bms:goodsType:info")
    public Result<List<BmsGoodsTypeDTO>> list() {
        Map<String, Object> params = new HashMap<>();
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsGoodsTypeDTO> list = bmsGoodsTypeService.list(params);
        List<BmsGoodsTypeDTO> result = new ArrayList<>();
        BmsGoodsTypeDTO goodsTypeDTO = new BmsGoodsTypeDTO();
        goodsTypeDTO.setId(BmsConstant.EMPTY_ID);
        goodsTypeDTO.setName(BmsConstant.EMPTY_NAME);
        result.add(goodsTypeDTO);
        result.addAll(list);
        return new Result<List<BmsGoodsTypeDTO>>().ok(result);
    }

}