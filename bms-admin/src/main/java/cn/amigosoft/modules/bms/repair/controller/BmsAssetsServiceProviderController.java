package cn.amigosoft.modules.bms.repair.controller;

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
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsServiceProviderEntity;
import cn.amigosoft.modules.bms.repair.excel.BmsAssetsServiceProviderExcel;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsServiceProviderService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 服务商表
 */
@RestController
@RequestMapping("bms/assetsServiceProvider")
@Api(tags = "服务商表 ")
public class BmsAssetsServiceProviderController {

    @Autowired
    private BmsAssetsServiceProviderService bmsAssetsServiceProviderService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsServiceProvider:page")
    @LogOperation("服务商管理-分页")
    public Result<PageData<BmsAssetsServiceProviderDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return bmsAssetsServiceProviderService.getPage(params);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("服务商管理-详情")
    @RequiresPermissions("bms:assetsServiceProvider:info")
    public Result<BmsAssetsServiceProviderDTO> get(@PathVariable("id") Long id) {
        BmsAssetsServiceProviderDTO data = bmsAssetsServiceProviderService.selectAssetsServiceProviderById(id);

        return new Result<BmsAssetsServiceProviderDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("服务商管理-保存")
    @RequiresPermissions("bms:assetsServiceProvider:save")
    public Result save(@RequestBody BmsAssetsServiceProviderDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsAssetsServiceProviderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsAssetsServiceProviderService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该服务商名称已存在");
        }
        bmsAssetsServiceProviderService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("服务商管理-修改")
    @RequiresPermissions("bms:assetsServiceProvider:update")
    public Result update(@RequestBody BmsAssetsServiceProviderDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsAssetsServiceProviderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsAssetsServiceProviderEntity data = bmsAssetsServiceProviderService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该服务商名称已存在");
        }
        bmsAssetsServiceProviderService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("服务商管理-删除")
    @RequiresPermissions("bms:assetsServiceProvider:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsAssetsServiceProviderEntity entity = new BmsAssetsServiceProviderEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsAssetsServiceProviderService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("服务商管理-导出")
    @RequiresPermissions("bms:assetsServiceProvider:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsServiceProviderDTO> list = bmsAssetsServiceProviderService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "服务商信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsServiceProviderExcel.class);
    }

}