/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.controller;

import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.sys.dto.SysDictDTO;
import cn.amigosoft.modules.sys.service.SysDictService;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/dict")
@Api(tags = "数据字典")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("page")
    @ApiOperation("字典分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createStartDate", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createEndDate", value = "结束时间", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public Result<PageData<SysDictDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        //字典分类
        PageData<SysDictDTO> page = sysDictService.page(params);

        return new Result<PageData<SysDictDTO>>().ok(page);
    }

    @PostMapping("list")
    @ApiOperation("字典分类数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType = "String")
    })
    public Result<List<SysDictDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        //字典分类数据
        List<SysDictDTO> list = sysDictService.list(params);

        return new Result<List<SysDictDTO>>().ok(list);
    }

    @GetMapping("list")
    @ApiOperation("字典分类数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType = "String")
    })
    public Result<List<SysDictDTO>> getList(@ApiIgnore @RequestParam Map<String, Object> params) {
        //字典分类数据
        List<SysDictDTO> list = sysDictService.list(params);

        return new Result<List<SysDictDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:dict:info")
    public Result<SysDictDTO> get(@PathVariable("id") Long id) {
        SysDictDTO data = sysDictService.get(id);

        return new Result<SysDictDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("数据字典-保存")
    @RequiresPermissions("sys:dict:save")
    public Result save(@RequestBody SysDictDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("数据字典-修改")
    @RequiresPermissions("sys:dict:update")
    public Result update(@RequestBody SysDictDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("数据字典-删除")
    @RequiresPermissions("sys:dict:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictService.delete(ids);

        return new Result();
    }

    @GetMapping("listByDictType/{dictType}")
    @ApiOperation("根据字典类型获取字典")
    public Result<List<SysDictDTO>> listByDictType(@ApiIgnore @PathVariable String dictType) {

        List<SysDictDTO> list = sysDictService.listByDictType(dictType);

        return new Result<List<SysDictDTO>>().ok(list);
    }
}