package cn.amigosoft.modules.bms.visit.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.bms.visit.service.BmsVisitApplyVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 人员报备审批表
 */
@RestController
@RequestMapping("bms/visitApplyVerify")
@Api(tags = "人员报备审批表 ")
public class BmsVisitApplyVerifyController {

    @Autowired
    private BmsVisitApplyVerifyService bmsVisitApplyVerifyService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("访客报备审批 -主管审批")
    @RequiresPermissions(value = {"bms:visitApplyVerify:save", "bms:visitApplyVerify:guard"}, logical = Logical.OR)
    public Result save(@RequestBody BmsVisitApplyVerifyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        return bmsVisitApplyVerifyService.insertVisitApplyVerify(dto);
    }

}