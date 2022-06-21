package cn.amigosoft.modules.dining.controller;

//import cn.amigosoft.common.annotation.LogOperation;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.dining.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.dining.service.BmsEvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * 评价表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-18
 */
@RestController
@RequestMapping("dining/evaluation")
@Api(tags="评价表 ")
public class BmsEvaluationController {
    @Autowired
    private BmsEvaluationService bmsEvaluationService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @LogOperation("小程序-用餐评价-分页")
    public Result<PageData<BmsEvaluationDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsEvaluationDTO> page = bmsEvaluationService.queryPage(params);

        return new Result<PageData<BmsEvaluationDTO>>().ok(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("信息")
    @LogOperation("小程序-用餐评价-详情")
    public Result<BmsEvaluationDTO> get(@PathVariable("id") Long id){
        BmsEvaluationDTO data = bmsEvaluationService.getDetail(id);

        return new Result<BmsEvaluationDTO>().ok(data);
    }

    @PutMapping("evaluate")
    @ApiOperation("评价")
    @LogOperation("小程序-用餐评价-评价")
    public Result evaluate(@RequestBody BmsEvaluationDTO dto) {
        //校验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);
        Result result = bmsEvaluationService.evaluate(dto);
        return result;
    }


}