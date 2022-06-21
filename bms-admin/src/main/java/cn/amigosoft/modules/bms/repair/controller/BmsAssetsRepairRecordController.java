package cn.amigosoft.modules.bms.repair.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsRepairRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 资产维修记录表
 */
@RestController
@RequestMapping("bms/assetsRepairRecord")
@Api(tags = "资产维修记录表 ")
public class BmsAssetsRepairRecordController {
    @Autowired
    private BmsAssetsRepairRecordService bmsAssetsRepairRecordService;

    @PostMapping("verify")
    @ApiOperation("保存")
    @LogOperation("资产报修-审核")
    @RequiresPermissions("bms:assetsRepairRecord:verify")
    public Result verify(@RequestBody BmsAssetsRepairRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsAssetsRepairRecordService.insertAssetsRepairRecord(dto);
    }

    @PostMapping("deal")
    @ApiOperation("保存")
    @LogOperation("资产报修-处理")
    @RequiresPermissions("bms:assetsRepairRecord:deal")
    public Result deal(@RequestBody BmsAssetsRepairRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsAssetsRepairRecordService.insertAssetsRepairRecord(dto);
    }

    @PostMapping("evaluation")
    @ApiOperation("保存")
    @LogOperation("资产报修-评价")
    @RequiresPermissions("bms:assetsRepair:save")
    public Result evaluation(@RequestBody BmsAssetsRepairRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsAssetsRepairRecordService.insertAssetsRepairRecord(dto);
    }

}