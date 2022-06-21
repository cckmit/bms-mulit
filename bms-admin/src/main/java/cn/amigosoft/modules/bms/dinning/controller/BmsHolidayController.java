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
import cn.amigosoft.modules.bms.dinning.dto.BmsHolidayDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsHolidayEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsHolidayExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsHolidayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 节假日表
 */
@RestController
@RequestMapping("bms/holiday")
@Api(tags = "节假日表 ")
public class BmsHolidayController {

    @Autowired
    private BmsHolidayService bmsHolidayService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:holiday:page")
    public Result<PageData<BmsHolidayDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsHolidayDTO> page = bmsHolidayService.page(params);

        return new Result<PageData<BmsHolidayDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:holiday:info")
    public Result<BmsHolidayDTO> get(@PathVariable("id") Long id) {
        BmsHolidayDTO data = bmsHolidayService.get(id);

        return new Result<BmsHolidayDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("节假日表 -保存")
    @RequiresPermissions("bms:holiday:save")
    public Result save(@RequestBody BmsHolidayDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsHolidayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("holiday", dto.getHoliday());
        Integer count = bmsHolidayService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该假日已存在");
        }
        bmsHolidayService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("节假日表 -修改")
    @RequiresPermissions("bms:holiday:update")
    public Result update(@RequestBody BmsHolidayDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsHolidayService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("节假日表 -删除")
    @RequiresPermissions("bms:holiday:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsHolidayService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("节假日表 -导出")
    @RequiresPermissions("bms:holiday:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsHolidayDTO> list = bmsHolidayService.list(params);

        ExcelUtils.exportExcelToTarget(response, "节假日信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsHolidayExcel.class);
    }

    @PostMapping("import")
    @ApiOperation(value = "上传文件")
    @RequiresPermissions("bms:holiday:import")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        bmsHolidayService.importExcel(file, response);
    }
}
