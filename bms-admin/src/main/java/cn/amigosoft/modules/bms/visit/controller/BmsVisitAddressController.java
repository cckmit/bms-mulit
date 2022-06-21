package cn.amigosoft.modules.bms.visit.controller;

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
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressEntity;
import cn.amigosoft.modules.bms.visit.excel.BmsVisitAddressExcel;
import cn.amigosoft.modules.bms.visit.service.BmsVisitAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 访问地点表
 */
@RestController
@RequestMapping("bms/visitAddress")
@Api(tags = "访问地点表 ")
public class BmsVisitAddressController {
    @Autowired
    private BmsVisitAddressService bmsVisitAddressService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("访问地点管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:visitAddress:page")
    public Result<PageData<BmsVisitAddressDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BmsVisitAddressDTO> page = bmsVisitAddressService.page(params);

        return new Result<PageData<BmsVisitAddressDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    @LogOperation("访问地点管理-详情")
    @RequiresPermissions("bms:visitAddress:info")
    public Result<BmsVisitAddressDTO> get(@PathVariable("id") Long id) {
        BmsVisitAddressDTO data = bmsVisitAddressService.selectAddressById(id);

        return new Result<BmsVisitAddressDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("访问地点管理-保存")
    @RequiresPermissions("bms:visitAddress:save")
    public Result save(@RequestBody BmsVisitAddressDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        QueryWrapper<BmsVisitAddressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        Integer count = bmsVisitAddressService.selectCount(queryWrapper);
        if (count > 0) {
            return new Result().error("该食堂名称已存在");
        }
        return bmsVisitAddressService.insertAddress(dto);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("访问地点管理-修改")
    @RequiresPermissions("bms:visitAddress:update")
    public Result update(@RequestBody BmsVisitAddressDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        QueryWrapper<BmsVisitAddressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName());
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        BmsVisitAddressEntity data = bmsVisitAddressService.selectOne(queryWrapper);
        if (data != null && (!data.getId().equals(dto.getId()))) {
            return new Result().error("该食堂名称已存在");
        }
        return bmsVisitAddressService.updateAddress(dto);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("访问地点管理-删除")
    @RequiresPermissions("bms:visitAddress:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            BmsVisitAddressEntity entity = new BmsVisitAddressEntity();
            entity.setId(id);
            entity.setDel(BmsConstant.DEL);
            bmsVisitAddressService.updateById(entity);
        }

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("访问地点管理-导出")
    @RequiresPermissions("bms:visitAddress:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsVisitAddressDTO> list = bmsVisitAddressService.list(params);

        ExcelUtils.exportExcelToTarget(response, "访问地点信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsVisitAddressExcel.class);
    }

    @GetMapping("list")
    @ApiOperation("查询")
    public Result<List<BmsVisitAddressDTO>> list() {
        Map<String, Object> params = new HashedMap();
        params.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsVisitAddressDTO> list = bmsVisitAddressService.list(params);
        return new Result<List<BmsVisitAddressDTO>>().ok(list);
    }
}
