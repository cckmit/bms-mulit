package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.dining.service.BmsMealTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 餐别表
 */
@RestController
@RequestMapping("bms/mealType")
@Api(tags = "餐别表 ")
public class BmsMealTypeController {

    @Autowired
    private BmsMealTypeService bmsMealTypeService;


    @GetMapping("list")
    @ApiOperation("餐别表-信息列表")
    public Result<List<BmsMealTypeEntity>> list() {
        QueryWrapper<BmsMealTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        List<BmsMealTypeEntity> list = bmsMealTypeService.selectList(wrapper);
        return new Result<List<BmsMealTypeEntity>>().ok(list);
    }

}