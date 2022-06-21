package cn.amigosoft.modules.area.controller;

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
import cn.amigosoft.modules.area.dto.BmsAreaDTO;
import cn.amigosoft.modules.area.entity.BmsAreaEntity;
import cn.amigosoft.modules.area.service.BmsAreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * 区域表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-18
 */
@RestController
@RequestMapping("bms/area")
@Api(tags="区域表 ")
public class BmsAreaController {
    @Autowired
    private BmsAreaService bmsAreaService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("activity:bmsarea:page")
    public Result<PageData<BmsAreaDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BmsAreaDTO> page = bmsAreaService.page(params);

        return new Result<PageData<BmsAreaDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("省")
    public Result<List<BmsAreaEntity>> get() {
        QueryWrapper<BmsAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        queryWrapper.isNull("pid");
        List<BmsAreaEntity> list = bmsAreaService.selectList(queryWrapper);
        return new Result<List<BmsAreaEntity>>().ok(list);
    }

    @GetMapping("cityList")
    @ApiOperation("市")
    public Result<List<BmsAreaEntity>> getCityList(Long pid) {
        QueryWrapper<BmsAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        queryWrapper.eq("pid", pid);
        List<BmsAreaEntity> list = bmsAreaService.selectList(queryWrapper);
        return new Result<List<BmsAreaEntity>>().ok(list);
    }

    @GetMapping("allCityList")
    @ApiOperation("市")
    public Result<List<BmsAreaEntity>> allCityList() {
        QueryWrapper<BmsAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        queryWrapper.isNotNull("pid");
        List<BmsAreaEntity> list = bmsAreaService.selectList(queryWrapper);
        return new Result<List<BmsAreaEntity>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("activity:bmsarea:info")
    public Result<BmsAreaDTO> get(@PathVariable("id") Long id){
        BmsAreaDTO data = bmsAreaService.get(id);

        return new Result<BmsAreaDTO>().ok(data);
    }

}