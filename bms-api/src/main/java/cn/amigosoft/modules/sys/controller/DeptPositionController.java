package cn.amigosoft.modules.sys.controller;

import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.sys.dto.DeptPositionDTO;
import cn.amigosoft.modules.sys.service.DeptPositionService;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
//import cn.amigosoft.modules.sys.excel.DeptPositionExcel;
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
 * 部门职位表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-17
 */
@RestController
@RequestMapping("sys/deptposition")
@Api(tags = "部门职位表 ")
public class DeptPositionController {

    @Autowired
    private DeptPositionService deptPositionService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("deptPosition:deptposition:page")
    public Result<PageData<DeptPositionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<DeptPositionDTO> page = deptPositionService.page(params);

        return new Result<PageData<DeptPositionDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("deptPosition:deptposition:info")
    public Result<DeptPositionDTO> get(@PathVariable("id") Long id) {
        DeptPositionDTO data = deptPositionService.get(id);

        return new Result<DeptPositionDTO>().ok(data);
    }

    @GetMapping("deptposition/{id}")
    @ApiOperation("部门职位列表")
    @RequiresPermissions("deptPosition:deptposition:info")
    public Result<List<DeptPositionDTO>> getPositionList(@PathVariable("id") Long id) {
        List<DeptPositionDTO> data = deptPositionService.getPositionList(id);
        return new Result<List<DeptPositionDTO>>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("部门职位表 -保存")
    @RequiresPermissions("deptPosition:deptposition:save")
    public Result save(@RequestBody DeptPositionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        deptPositionService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("部门职位表 -修改")
    @RequiresPermissions("deptPosition:deptposition:update")
    public Result update(@RequestBody DeptPositionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        deptPositionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("部门职位表 -删除")
    @RequiresPermissions("deptPosition:deptposition:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        deptPositionService.delete(ids);

        return new Result();
    }
//
//    @GetMapping("export")
//    @ApiOperation("导出")
//    @LogOperation("部门职位表 -导出")
//    @RequiresPermissions("deptPosition:deptposition:export")
//    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<DeptPositionDTO> list = deptPositionService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, list, DeptPositionExcel.class);
//    }

}