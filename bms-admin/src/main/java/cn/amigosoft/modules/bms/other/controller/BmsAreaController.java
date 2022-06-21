package cn.amigosoft.modules.bms.other.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.other.dto.BmsAreaDTO;
import cn.amigosoft.modules.bms.other.service.BmsAreaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 区域表
 */
@RestController
@RequestMapping("bms/area")
@Api(tags = "区域表 ")
public class BmsAreaController {

    @Autowired
    private BmsAreaService bmsAreaService;

    @GetMapping("list")
    public Result<List<BmsAreaDTO>> list() {
        List<BmsAreaDTO> list = bmsAreaService.selectAreaList();

        return new Result<List<BmsAreaDTO>>().ok(list);
    }

}