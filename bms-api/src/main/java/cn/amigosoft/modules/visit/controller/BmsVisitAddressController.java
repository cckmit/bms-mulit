package cn.amigosoft.modules.visit.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitAddressEntity;
import cn.amigosoft.modules.visit.service.BmsVisitAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 访问地点表
 */
@RestController
@RequestMapping("bms/visitAddress")
@Api(tags = "访问地点表 ")
public class BmsVisitAddressController {
    @Autowired
    private BmsVisitAddressService visitAddressService;

    @GetMapping("list")
    @ApiOperation("访问地点信息")
    public Result<List<BmsVisitAddressEntity>> get() {
        QueryWrapper<BmsVisitAddressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        List<BmsVisitAddressEntity> list = visitAddressService.selectList(queryWrapper);
        return new Result<List<BmsVisitAddressEntity>>().ok(list);
    }

    @GetMapping("chooseList")
    @ApiOperation("访问地点信息")
    public Result<List<BmsVisitAddressDTO>> getChooseList() {
        List<BmsVisitAddressDTO> list = visitAddressService.getChooseList();
        return new Result<List<BmsVisitAddressDTO>>().ok(list);
    }

}