package cn.amigosoft.modules.sys.controller;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.sys.dto.SysAppMenuDTO;
import cn.amigosoft.modules.sys.service.SysAppMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 小程序菜单表
 */
@RestController
@RequestMapping("bms/appMenu")
@Api(tags = "小程序菜单表 ")
public class SysAppMenuController {
    @Autowired
    private SysAppMenuService sysAppMenuService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<SysAppMenuDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysAppMenuDTO> page = sysAppMenuService.page(params);

        return new Result<PageData<SysAppMenuDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SysAppMenuDTO> get(@PathVariable("id") Long id) {
        SysAppMenuDTO data = sysAppMenuService.get(id);

        return new Result<SysAppMenuDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody SysAppMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysAppMenuService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody SysAppMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysAppMenuService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysAppMenuService.delete(ids);

        return new Result();
    }

    @GetMapping("userMenu")
    @ApiOperation("用户菜单")
    public Result<List<SysAppMenuDTO>> userMenu(HttpServletRequest request) {
        List<SysAppMenuDTO> list = sysAppMenuService.selectMenuByUserInfo(request);
        return new Result<List<SysAppMenuDTO>>().ok(list);
    }

}