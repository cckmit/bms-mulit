package cn.amigosoft.modules.assets.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeDTO;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeTreeDTO;
import cn.amigosoft.modules.assets.service.AssetsEquipmentTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资产设备分类表  前端控制器
 * </p>
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
@Api(tags = {"资产设备分类表 模块-资产设备分类表 接口"})
@RestController
@RequestMapping("/assets/assetsEquipmentType")
public class AssetsEquipmentTypeController {


    @Autowired
    private AssetsEquipmentTypeService assetsEquipmentTypeService;

    @GetMapping("page")
    @ApiOperation("首页")
    @RequiresPermissions("assets:assetsEquipmentType:page")
    public Result<List<AssetsEquipmentTypeTreeDTO>> page() {
        List<AssetsEquipmentTypeTreeDTO> list = assetsEquipmentTypeService.pageList();

        return new Result<List<AssetsEquipmentTypeTreeDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("assets:assetsEquipmentType:info")
    public Result<AssetsEquipmentTypeDTO> get(@PathVariable("id") Long id) {
        AssetsEquipmentTypeDTO data = assetsEquipmentTypeService.get(id);

        return new Result<AssetsEquipmentTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("资产设备分类表 -保存")
    @RequiresPermissions("assets:assetsEquipmentType:save")
    public Result save(@RequestBody AssetsEquipmentTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        Result result = assetsEquipmentTypeService.saveEquipmentType(dto);

        return result;
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产设备分类表 -修改")
    @RequiresPermissions("assets:assetsEquipmentType:update")
    public Result update(@RequestBody AssetsEquipmentTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        Result result = assetsEquipmentTypeService.updateEquipmentType(dto);

        return result;
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产设备分类表 -删除")
    @RequiresPermissions("assets:assetsEquipmentType:delete")
    public Result delete(@RequestBody Long[] ids) {

        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        Result result = assetsEquipmentTypeService.deleteEquipmentType(ids[0]);

        return result;
    }

    @GetMapping("listTreeSelect")
    @ApiOperation("一二级除物联设备类型下拉框")
    @RequiresPermissions("assets:assetsEquipmentType:page")
    public Result<List<AssetsEquipmentTypeTreeDTO>> listTreeSelect() {
        List<AssetsEquipmentTypeTreeDTO> list = assetsEquipmentTypeService.listTreeSelect();

        return new Result<List<AssetsEquipmentTypeTreeDTO>>().ok(list);
    }

    @GetMapping("allTypeTree")
    @ApiOperation("全部类型树")
    @RequiresPermissions("assets:assetsEquipmentType:page")
    public Result<List<AssetsEquipmentTypeTreeDTO>> allTypeTree() {
        List<AssetsEquipmentTypeTreeDTO> list = assetsEquipmentTypeService.allTypeTree();

        return new Result<List<AssetsEquipmentTypeTreeDTO>>().ok(list);
    }

    @GetMapping("universalTypeTree")
    @ApiOperation("通用类型树")
    @RequiresPermissions("assets:assetsEquipmentType:page")
    public Result<List<AssetsEquipmentTypeTreeDTO>> universalTypeTree() {
        List<AssetsEquipmentTypeTreeDTO> list = assetsEquipmentTypeService.universalTypeTree();

        return new Result<List<AssetsEquipmentTypeTreeDTO>>().ok(list);
    }

}
