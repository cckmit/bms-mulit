package cn.amigosoft.modules.dining.controller;

import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsItemDTO;
import cn.amigosoft.modules.dining.service.BmsItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 餐品表
 */
@RestController
@RequestMapping("bms/item")
@Api(tags = "餐品表 ")
public class BmsItemController {

    @Autowired
    private BmsItemService bmsItemService;

    @GetMapping("list")
    @ApiOperation("餐品管理-信息列表")
    public Result<List<BmsItemDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<BmsItemDTO> list = bmsItemService.selectItemList(params);
        return new Result<List<BmsItemDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("餐品管理-信息")
    @LogOperation("小程序-餐品-详情")
    public Result<BmsItemDTO> get(@PathVariable("id") Long id) {
        BmsItemDTO data = bmsItemService.selectItemById(id);
        return new Result<BmsItemDTO>().ok(data);
    }

    @GetMapping("detail")
    @ApiOperation("餐品管理-信息")
    public Result<BmsItemDTO> detail(Long id) {
        BmsItemDTO data = bmsItemService.selectItemById(id);
        return new Result<BmsItemDTO>().ok(data);
    }

    @GetMapping("idsDetail")
    @ApiOperation("餐品管理-信息")
    public Result<List<BmsItemDTO>> idsDetail(Long[] ids) {
        List<BmsItemDTO> list = bmsItemService.selectItemByIds(ids);
        return new Result<List<BmsItemDTO>>().ok(list);
    }

    @GetMapping("search/list")
    @ApiOperation("小程序-报餐人数统计-餐品搜索项")
    public Result<List<BmsItemDTO>> itemList() {
        List<BmsItemDTO> list = bmsItemService.getItemList();
        return new Result<List<BmsItemDTO>>().ok(list);
    }
}