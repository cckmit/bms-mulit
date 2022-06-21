package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.bms.dinning.service.BmsReceptionMealVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 接待餐审批表
 */
@RestController
@RequestMapping("bms/receptionMealVerify")
@Api(tags = "接待餐审批表 ")
public class BmsReceptionMealVerifyController {

    @Autowired
    private BmsReceptionMealVerifyService bmsReceptionMealVerifyService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("接待餐审批-保存")
    @RequiresPermissions(value = {"bms:receptionMeal:managerVerify", "bms:receptionMeal:officeVerify"}, logical = Logical.OR)
    public Result save(@RequestBody BmsReceptionMealVerifyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return bmsReceptionMealVerifyService.insertReceptionMealVerify(dto);
    }

}