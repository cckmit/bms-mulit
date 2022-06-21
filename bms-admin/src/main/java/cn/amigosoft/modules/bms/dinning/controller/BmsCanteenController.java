package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dto.BmsCanteenDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsCanteenEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsCanteenExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsCanteenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 食堂管理
 */
@RestController
@RequestMapping("bms/canteen")
@Api(tags = "食堂管理")
public class BmsCanteenController {

    @Autowired
    private BmsCanteenService bmsCanteenService;

    @GetMapping("page")
    @ApiOperation("食堂管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:canteen:page")
    @LogOperation("食堂管理-分页")
    public Result<PageData<BmsCanteenDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsCanteenDTO> page = bmsCanteenService.page(params);
        return new Result<PageData<BmsCanteenDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("食堂管理-详情")
    @LogOperation("食堂管理-详情")
    @RequiresPermissions("bms:canteen:info")
    public Result<BmsCanteenDTO> get(@PathVariable("id") Long id) {
        BmsCanteenDTO data = bmsCanteenService.selectCanteenById(id);

        return new Result<BmsCanteenDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("食堂管理-保存")
    @RequiresPermissions("bms:canteen:save")
    public Result save(@RequestBody BmsCanteenDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsCanteenEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsCanteenService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该食堂名称已存在");
        }
        bmsCanteenService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("食堂管理-修改")
    @RequiresPermissions("bms:canteen:update")
    public Result update(@RequestBody BmsCanteenDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsCanteenEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsCanteenEntity data = bmsCanteenService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该食堂名称已存在");
        }
        bmsCanteenService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("食堂管理-删除")
    @RequiresPermissions("bms:canteen:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsCanteenEntity entity = new BmsCanteenEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsCanteenService.updateById(entity);
        }
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("食堂管理-导出")
    @RequiresPermissions("bms:canteen:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsCanteenDTO> list = bmsCanteenService.export(params);
        ExcelUtils.exportExcelToTarget(response, "食堂信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsCanteenExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("食堂管理-食堂列表")
    public Result<List<BmsCanteenDTO>> list() {
        List<BmsCanteenDTO> list = bmsCanteenService.baseInfoList();
        return new Result<List<BmsCanteenDTO>>().ok(list);
    }


    @GetMapping("downloadQRCodeZip")
    @ApiOperation("导出-下载二维码")
    @LogOperation("食堂管理-导出-下载二维码")
    @RequiresPermissions("bms:canteen:export")
    public void downloadQRCodeZip(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {
        try {
            bmsCanteenService.downloadQRCodeZip(params, response, request);
        } catch (Exception e) {
            throw new RenException("二维码压缩包下载失败");
        }
    }
}