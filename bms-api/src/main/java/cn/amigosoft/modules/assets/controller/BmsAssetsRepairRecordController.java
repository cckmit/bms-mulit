package cn.amigosoft.modules.assets.controller;

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
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.assets.service.BmsAssetsRepairRecordService;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 资产维修记录表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@RestController
@RequestMapping("bms/assetsRepairRecord")
@Api(tags="资产维修记录表 ")
public class BmsAssetsRepairRecordController {
    @Autowired
    private BmsAssetsRepairRecordService bmsAssetsRepairRecordService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("activity:bmsassetsrepairrecord:page")
    public Result<PageData<BmsAssetsRepairRecordDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAssetsRepairRecordDTO> page = bmsAssetsRepairRecordService.page(params);

        return new Result<PageData<BmsAssetsRepairRecordDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("activity:bmsassetsrepairrecord:info")
    public Result<BmsAssetsRepairRecordDTO> get(@PathVariable("id") Long id){
        BmsAssetsRepairRecordDTO data = bmsAssetsRepairRecordService.get(id);

        return new Result<BmsAssetsRepairRecordDTO>().ok(data);
    }

    @PostMapping("verify")
    @ApiOperation("审核")
    @LogOperation("小程序-报修审批-审核提交")
    //@RequiresPermissions("activity:bmsassetsrepairrecord:save")
    public Result saveVerify(@RequestBody BmsAssetsRepairRecordDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsRepairRecordService.addVerifyRecord(dto);

        return new Result();
    }

    @PostMapping("deal")
    @ApiOperation("处理")
    @LogOperation("小程序-维修管理-处理提交")
    //@RequiresPermissions("activity:bmsassetsrepairrecord:save")
    public Result saveDeal(@RequestBody BmsAssetsRepairRecordDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsAssetsRepairRecordService.addDealRecord(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("资产维修记录表 -修改")
    @RequiresPermissions("activity:bmsassetsrepairrecord:update")
    public Result update(@RequestBody BmsAssetsRepairRecordDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsAssetsRepairRecordService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("资产维修记录表 -删除")
    @RequiresPermissions("activity:bmsassetsrepairrecord:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsAssetsRepairRecordService.delete(ids);

        return new Result();
    }

    @GetMapping("repairService")
    @ApiOperation("内部维修人员")
    //@LogOperation("资产维修记录表-内部维修人员")
    public Result<List<SysUserDTO>> getRepairServiceList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<SysUserDTO> list = bmsAssetsRepairRecordService.getRepairServiceList(params);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("externalService")
    @ApiOperation("内部维修人员")
    //@LogOperation("资产维修记录表-内部维修人员")
    public Result<List<SysUserDTO>> getExternalServiceList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<SysUserDTO> list = bmsAssetsRepairRecordService.getExternalServiceList(params);
        return new Result<List<SysUserDTO>>().ok(list);
    }

}