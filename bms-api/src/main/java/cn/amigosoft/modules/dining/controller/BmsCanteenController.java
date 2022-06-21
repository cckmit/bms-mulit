package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsCanteenDTO;
import cn.amigosoft.modules.dining.service.BmsCanteenService;
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
 * 食堂管理
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-13
 */
@RestController
@RequestMapping("bms/canteen")
@Api(tags = "食堂管理")
public class BmsCanteenController {

    @Autowired
    private BmsCanteenService bmsCanteenService;

    @GetMapping("list")
    @ApiOperation("食堂管理-信息列表")
    public Result<List<BmsCanteenDTO>> list() {
        Map<String, Object> params = new HashMap<>();
        List<BmsCanteenDTO> list = bmsCanteenService.list(params);
        return new Result<List<BmsCanteenDTO>>().ok(list);
    }

}