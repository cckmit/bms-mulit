package cn.amigosoft.modules.visit.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVisitorEntity;
import cn.amigosoft.modules.visit.service.BmsVisitApplyVisitorService;
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
 * 人员报备访客关联表 
 */
@RestController
@RequestMapping("bms/visitApplyVisitor")
@Api(tags="人员报备访客关联表 ")
public class BmsVisitApplyVisitorController {
    @Autowired
    private BmsVisitApplyVisitorService bmsVisitApplyVisitorService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("bms:visitApplyVisitor:page")
    public Result<PageData<BmsVisitApplyVisitorDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsVisitApplyVisitorDTO> page = bmsVisitApplyVisitorService.page(params);

        return new Result<PageData<BmsVisitApplyVisitorDTO>>().ok(page);
    }

    @GetMapping("visitorHistoryList")
    @ApiOperation("历史访客列表")
    public Result<List<BmsVisitApplyVisitorDTO>> visitorHistoryList() {
        List<BmsVisitApplyVisitorDTO> list = bmsVisitApplyVisitorService.getVisitorHistoryList();
        return new Result<List<BmsVisitApplyVisitorDTO>>().ok(list);
    }

    @GetMapping("visitorInfo")
    @ApiOperation("访客信息")
    public Result<BmsVisitApplyVisitorDTO> getVisitorInfo(@ApiIgnore @RequestParam Map<String, Object> params) {
        BmsVisitApplyVisitorDTO data = bmsVisitApplyVisitorService.getVisitorInfo(params);
        return new Result<BmsVisitApplyVisitorDTO>().ok(data);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("bms:visitApplyVisitor:info")
    public Result<BmsVisitApplyVisitorDTO> get(@PathVariable("id") Long id){
        BmsVisitApplyVisitorDTO data = bmsVisitApplyVisitorService.get(id);

        return new Result<BmsVisitApplyVisitorDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("人员报备访客关联表 -保存")
    @RequiresPermissions("bms:visitApplyVisitor:save")
    public Result save(@RequestBody BmsVisitApplyVisitorDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        bmsVisitApplyVisitorService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("人员报备访客关联表 -修改")
    @RequiresPermissions("bms:visitApplyVisitor:update")
    public Result update(@RequestBody BmsVisitApplyVisitorDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        bmsVisitApplyVisitorService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("人员报备访客关联表 -删除")
    @RequiresPermissions("bms:visitApplyVisitor:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        bmsVisitApplyVisitorService.delete(ids);

        return new Result();
    }

}