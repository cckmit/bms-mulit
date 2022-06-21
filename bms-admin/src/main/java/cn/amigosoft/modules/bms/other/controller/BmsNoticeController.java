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
import cn.amigosoft.modules.bms.other.dto.BmsNoticeDTO;
import cn.amigosoft.modules.bms.other.entity.BmsNoticeEntity;
import cn.amigosoft.modules.bms.other.excel.BmsNoticeExcel;
import cn.amigosoft.modules.bms.other.service.BmsNoticeService;
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
 * 通知表
 */
@RestController
@RequestMapping("bms/notice")
@Api(tags = "通知表 ")
public class BmsNoticeController {
    @Autowired
    private BmsNoticeService bmsNoticeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:notice:page")
    public Result<PageData<BmsNoticeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsNoticeDTO> page = bmsNoticeService.page(params);

        return new Result<PageData<BmsNoticeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:notice:info")
    public Result<BmsNoticeDTO> get(@PathVariable("id") Long id) {
        BmsNoticeDTO data = bmsNoticeService.get(id);

        return new Result<BmsNoticeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("通知表 -保存")
    @RequiresPermissions("bms:notice:save")
    public Result save(@RequestBody BmsNoticeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsNoticeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("通知表 -修改")
    @RequiresPermissions("bms:notice:update")
    public Result update(@RequestBody BmsNoticeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsNoticeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("通知表 -删除")
    @RequiresPermissions("bms:notice:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsNoticeEntity entity = new BmsNoticeEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsNoticeService.updateById(entity);
        }
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("通知表 -导出")
    @RequiresPermissions("bms:notice:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        params.put(BmsConstant.DEL_COLUMN_NAME,BmsConstant.NOT_DEL );
        List<BmsNoticeDTO> list = bmsNoticeService.list(params);
        ExcelUtils.exportExcelToTarget(response, "通知信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsNoticeExcel.class);
    }

}