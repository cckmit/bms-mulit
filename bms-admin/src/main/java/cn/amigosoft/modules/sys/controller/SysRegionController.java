/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.controller;

import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.sys.dto.RegionDTO;
import cn.amigosoft.modules.sys.dto.SysRegionDTO;
import cn.amigosoft.modules.sys.dto.region.RegionProvince;
import cn.amigosoft.modules.sys.service.SysRegionService;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/region")
@Api(tags = "行政区域")
public class SysRegionController {

    @Autowired
    private SysRegionService sysRegionService;

    @GetMapping("list")
    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "上级ID", paramType = "query", dataType = "String")
    })
    @PreAuthorize("hasAuthority('sys:region:list')")
    public Result<List<SysRegionDTO>> list(@RequestParam Map<String, Object> params) {
        List<SysRegionDTO> list = sysRegionService.list(params);

        return new Result<List<SysRegionDTO>>().ok(list);
    }

    @GetMapping("tree")
    @ApiOperation("树形数据")
    public Result<List<Map<String, Object>>> tree() {
        List<Map<String, Object>> list = sysRegionService.getTreeList();

        return new Result<List<Map<String, Object>>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @PreAuthorize("hasAuthority('sys:region:info')")
    public Result<SysRegionDTO> get(@PathVariable("id") Long id) {
        SysRegionDTO data = sysRegionService.get(id);

        return new Result<SysRegionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @PreAuthorize("hasAuthority('sys:region:save')")
    public Result save(@RequestBody SysRegionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysRegionService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @PreAuthorize("hasAuthority('sys:region:update')")
    public Result update(@RequestBody SysRegionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysRegionService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    @LogOperation("删除")
    @PreAuthorize("hasAuthority('sys:region:delete')")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        int count = sysRegionService.getCountByPid(id);
        if (count > 0) {
            throw new RenException(ErrorCode.REGION_SUB_DELETE_ERROR);
        }

        sysRegionService.delete(id);

        return new Result();
    }

    @GetMapping("region")
    @ApiOperation("地区列表")
    @ApiImplicitParam(name = "threeLevel", value = "是否显示3级   true显示   false不显示", paramType = "query", dataType = "boolean")
    public Result<List<RegionProvince>> region(@RequestParam(value = "threeLevel", defaultValue = "true") boolean threeLevel) {
        List<RegionProvince> list = sysRegionService.getRegion(threeLevel);

        return new Result<List<RegionProvince>>().ok(list);
    }

    @GetMapping("getRegionName")
    @ApiOperation("地区列表")
    public Result<RegionDTO> getRegionName(@RequestParam String code) {

        RegionDTO data = sysRegionService.getRegionName(code);

        return new Result<RegionDTO>().ok(data);
    }

}
