package cn.amigosoft.modules.bms.repair.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.utils.ExcelUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsTypeDTO;
import cn.amigosoft.modules.bms.other.dto.BmsTreeSelectDTO;
import cn.amigosoft.modules.bms.repair.excel.BmsAssetsTypeExcel;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsTypeService;
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
 * 资产类别表
 */
@RestController
@RequestMapping("bms/assetsType")
@Api(tags = "资产类别表 ")
public class BmsAssetsTypeController {
    @Autowired
    private BmsAssetsTypeService bmsAssetsTypeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @LogOperation("资产类别管理-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("bms:assetsType:page")
    public Result<List<BmsAssetsTypeDTO>> page() {
        List<BmsAssetsTypeDTO> list = bmsAssetsTypeService.selectAssetsTypeList();
        return new Result<List<BmsAssetsTypeDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @LogOperation("资产类别管理-详情")
    @RequiresPermissions("bms:assetsType:info")
    public Result<BmsAssetsTypeDTO> get(@PathVariable("id") Long id) {
        BmsAssetsTypeDTO data = bmsAssetsTypeService.get(id);

        return new Result<BmsAssetsTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产类别管理-保存")
    @RequiresPermissions("bms:assetsType:save")
    public Result save(@RequestBody BmsAssetsTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产类别管理-修改")
    @RequiresPermissions("bms:assetsType:update")
    public Result update(@RequestBody BmsAssetsTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产类别管理-删除")
    @RequiresPermissions("bms:assetsType:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsAssetsTypeService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("资产类别管理-导出")
    @RequiresPermissions("bms:assetsType:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BmsAssetsTypeDTO> list = bmsAssetsTypeService.selectExportList(params);

        ExcelUtils.exportExcelToTarget(response, "资产类别信息_" + new DateTime().toString("yyyyMMddHHmmss"), list, BmsAssetsTypeExcel.class);
    }

    @GetMapping("tree")
    @ApiOperation("资产类别表-资产类别树")
    public Result<List<BmsTreeSelectDTO>> assetsTypeTree() {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        List<BmsAssetsTypeDTO> list = bmsAssetsTypeService.list(map);
        List<BmsTreeSelectDTO> result = new ArrayList<>();
        for (BmsAssetsTypeDTO assetsTypeDTO : list) {
            result.add(changeAssetsTypeToTree(assetsTypeDTO));
        }
        return new Result<List<BmsTreeSelectDTO>>().ok(result);
    }

    private BmsTreeSelectDTO changeAssetsTypeToTree(BmsAssetsTypeDTO assetsTypeDTO) {
        BmsTreeSelectDTO treeSelectDTO = new BmsTreeSelectDTO();
        treeSelectDTO.setTitle(assetsTypeDTO.getName());
        String id = String.valueOf(assetsTypeDTO.getId());
        treeSelectDTO.setValue(id);
        treeSelectDTO.setKey(id);
        List<BmsAssetsTypeDTO> children = assetsTypeDTO.getChildren();
        if (children != null && children.size() > 0) {
            List<BmsTreeSelectDTO> list = new ArrayList<>();
            for (BmsAssetsTypeDTO child : children) {
                list.add(changeAssetsTypeToTree(child));
            }
            treeSelectDTO.setChildren(list);
        }
        return treeSelectDTO;
    }

}