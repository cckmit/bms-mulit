package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.dining.service.BmsReceptionMealVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接待餐审批表
 */
@RestController
@RequestMapping("dining/receptionMealVerify")
@Api(tags = "接待餐审批表 ")
public class BmsReceptionMealVerifyController {

    @Autowired
    private BmsReceptionMealVerifyService bmsReceptionMealVerifyService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("小程序-接待餐审批-审批")
    public Result save(@RequestBody BmsReceptionMealVerifyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        return bmsReceptionMealVerifyService.saveVerify(dto);
    }

}