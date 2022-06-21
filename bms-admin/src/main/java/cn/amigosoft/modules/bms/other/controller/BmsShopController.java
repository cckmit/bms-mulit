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
import cn.amigosoft.modules.bms.other.dto.BmsShopDTO;
import cn.amigosoft.modules.bms.other.entity.BmsShopEntity;
import cn.amigosoft.modules.bms.other.excel.BmsShopExcel;
import cn.amigosoft.modules.bms.other.service.BmsShopService;
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
 * 店铺表
 */
@RestController
@RequestMapping("bms/shop")
@Api(tags = "店铺表 ")
public class BmsShopController {
    @Autowired
    private BmsShopService bmsShopService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:shop:page")
    public Result<PageData<BmsShopDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsShopService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:shop:info")
    public Result<BmsShopDTO> get(@PathVariable("id") Long id) {
        BmsShopDTO data = bmsShopService.selectShopById(id);

        return new Result<BmsShopDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("店铺表 -保存")
    @RequiresPermissions("bms:shop:save")
    public Result save(@RequestBody BmsShopDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsShopService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("店铺表 -修改")
    @RequiresPermissions("bms:shop:update")
    public Result update(@RequestBody BmsShopDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsShopService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("店铺表 -删除")
    @RequiresPermissions("bms:shop:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsShopEntity entity = new BmsShopEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsShopService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("店铺表 -导出")
    @RequiresPermissions("bms:shop:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsShopDTO> list = bmsShopService.selectExportList(params);
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);

        ExcelUtils.exportExcelToTarget(response, "店铺信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsShopExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    @LogOperation("店铺表 -列表")
    @RequiresPermissions("bms:shop:info")
    public Result<List<BmsShopDTO>> list() {
        Map<String, Object> params = new HashMap<>();
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);

        List<BmsShopDTO> list = bmsShopService.list(params);
        List<BmsShopDTO> result = new ArrayList<>();
        BmsShopDTO shopDTO = new BmsShopDTO();
        shopDTO.setId(BmsConstant.EMPTY_ID);
        shopDTO.setName(BmsConstant.EMPTY_NAME);
        result.add(shopDTO);
        result.addAll(list);
        return new Result<List<BmsShopDTO>>().ok(result);
    }

}