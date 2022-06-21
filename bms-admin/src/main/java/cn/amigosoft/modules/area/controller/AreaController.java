package cn.amigosoft.modules.area.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.ImportUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import cn.amigosoft.modules.area.common.FacilityType;
import cn.amigosoft.modules.area.dto.*;
import cn.amigosoft.modules.area.excel.AreaExcel;
import cn.amigosoft.modules.area.service.AreaInfoService;
import cn.amigosoft.modules.area.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 区域管理控制层
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
@RestController
@RequestMapping("area")
@Api(tags = "区域管理")
@Slf4j
public class AreaController {
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AreaService areaService;

    @GetMapping("/areainfo")
    @ApiOperation("社区信息")
    @RequiresPermissions("area:area:communityInfo")
    public Result<AreaDetailInfoDTO> getAreainfo() {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        AreaDetailInfoDTO areaDetailInfoDTO;
        if (areaIdResult.getCode() == 0) {
            Long areaId = areaIdResult.getData();
            areaDetailInfoDTO = areaInfoService.queryAreaInfo(areaId);
        } else {
            areaDetailInfoDTO = new AreaDetailInfoDTO();
        }
        return new Result<AreaDetailInfoDTO>().ok(areaDetailInfoDTO);
    }

    @GetMapping("page")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "areaName", value = "区域名称", paramType = "query", dataType = "String")
    })
//    @RequiresPermissions("area:area:page")
    public Result<PageData<AreaBriefInfoDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        if (areaIdResult.getCode() != 0) {
            int code = areaIdResult.getCode();
            return new Result<PageData<AreaBriefInfoDTO>>().error(code);
        }

        Long areaId = areaIdResult.getData();
        params.put("areaId", areaId);
        PageData<AreaBriefInfoDTO> pageData = areaService.query(params);

        return new Result<PageData<AreaBriefInfoDTO>>().ok(pageData);
    }

    @GetMapping("tree")
    @ApiOperation("查询树形结构数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaType", value = "二级节点类型,可为空", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "identity", value = "是否显示本身节点,默认显示", paramType = "query", dataType = "boolean")
    })
//    @RequiresPermissions("area:area:page")
    public Result<List<TreeData>> queryTreeData(String areaType,
                                                @RequestParam(defaultValue = "true") boolean identity) {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        List<TreeData> treeDataList;

        if (areaIdResult.getCode() == 0) {
            Long areaId = areaIdResult.getData();
            treeDataList = areaService.queryTreeData(areaId, identity, areaType);
        } else {
            treeDataList = new ArrayList<>();
        }
        return new Result<List<TreeData>>().ok(treeDataList);
    }

    @GetMapping("areaTree")
    @ApiOperation("区域下拉框")
//    @RequiresPermissions("area:area:page")
    public Result<List<AreaTreeData>> queryAllByType() {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        List<AreaTreeData> treeDataList;

        if (areaIdResult.getCode() == 0) {
            Long areaId = areaIdResult.getData();

            //要查询的区域类型
            String excludeType = Constant.AreaType.BULIDING.getValue() + "," + Constant.AreaType.FLOOR.getValue() + "," + Constant.AreaType.ROOM.getValue() + "," + Constant.AreaType.PARTITION.getValue();
            treeDataList = areaService.queryAllByType(excludeType, Constant.AreaIsDel.VALID, areaId, null, null);
        } else {
            treeDataList = new ArrayList<>();
        }
        return new Result<List<AreaTreeData>>().ok(treeDataList);
    }


    @GetMapping("/list/area")
    @ApiOperation("查询区域列表信息")
//    @RequiresPermissions("area:area:page")
    public Result<Object> queryAreaList() {
        List<AreaInfoDTO> list = areaInfoService.queryAreaList();

        List<TreeData> treeDataList = new ArrayList<>();
        for (AreaInfoDTO areaDTO : list) {
            TreeData treeData = new TreeData();
            treeData.setId(areaDTO.getId());
            treeData.setName(areaDTO.getAreaName());

            treeDataList.add(treeData);
        }

        return new Result<>().ok(treeDataList);
    }

    @GetMapping("/getTopArea")
    @ApiOperation("查询区域列表信息")
//    @RequiresPermissions("area:area:page")
    public Result getTopArea() {
        List<AreaDTO> areaDTOList = areaService.getTopArea();
        return new Result().ok(areaDTOList);
    }

    @GetMapping("/list/facility")
    @ApiOperation("查询设施列表信息")
//    @RequiresPermissions("area:area:page")
    public Result<Object> queryFacilityIdList() {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        List<TreeData> treeDataList;

        if (areaIdResult.getCode() == 0) {
            Long areaId = areaIdResult.getData();
            treeDataList = areaService.queryFacilityData(areaId);
        } else {
            treeDataList = new ArrayList<>();
        }

        return new Result<>().ok(treeDataList);
    }

    @GetMapping("/list/building")
    @ApiOperation("查询楼栋列表信息")
//    @RequiresPermissions("area:area:page")
    public Result<List<TreeData>> queryBuildingList() {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        List<TreeData> treeDataList;

        if (areaIdResult.getCode() == 0) {
            Long areaId = areaIdResult.getData();
            treeDataList = areaService.queryBuildingFloorRoomData(areaId, FacilityType.BUILDING);
        } else {
            treeDataList = new ArrayList<>();
        }
        return new Result<List<TreeData>>().ok(treeDataList);
    }

    @GetMapping("/list/floor")
    @ApiOperation("查询楼层列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaFacilityId", value = "楼栋areaId", paramType = "query", dataType = "Long", required = true)
    })
//    @RequiresPermissions("area:area:page")
    public Result<Object> queryFloorList(long areaFacilityId) {
        List<TreeData> treeDataList = areaService.queryBuildingFloorRoomData(areaFacilityId, FacilityType.BUILDING_FLOOR);
        return new Result<>().ok(treeDataList);
    }

    @GetMapping("/list/room")
    @ApiOperation("查询房间列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaFacilityId", value = "楼层areaId", paramType = "query", dataType = "Long", required = true)
    })
//    @RequiresPermissions("area:area:page")
    public Result<Object> queryRoomList(long areaFacilityId) {
        List<TreeData> treeDataList = areaService.queryBuildingFloorRoomData(areaFacilityId, FacilityType.BUILDING_ROOM);
        return new Result<>().ok(treeDataList);
    }

    @GetMapping("/info/area")
    @ApiOperation("社区信息")
    @RequiresPermissions("area:area:communityDetail")
    public Result<AreaDetailInfoDTO> areaInfo() {
        // 获取用户所在的社区id
        Result<Long> areaIdResult = areaService.getAaeaIdByUser();
        if (areaIdResult.getCode() != 0) {
            String msg = areaIdResult.getMsg();
            return new Result<AreaDetailInfoDTO>().error(msg);
        }

        Long areaId = areaIdResult.getData();
        AreaDetailInfoDTO areaDetailInfoDTO = areaInfoService.queryAreaInfo(areaId);
        return new Result<AreaDetailInfoDTO>().ok(areaDetailInfoDTO);
    }

    @PutMapping("/update/areainfo")
    @ApiOperation("修改社区信息")
    @LogOperation("修改社区信息")
    @RequiresPermissions("area:area:updateCommunity")
    public Result<String> updateAreaInfo(@RequestBody AreaDTO areaDTO, @RequestBody AreaInfoDTO areaInfoDTO, @RequestBody Map<String, Object> params) {
        //效验数据
        ValidatorUtils.validateEntity(areaInfoDTO, UpdateGroup.class, DefaultGroup.class);

        areaInfoService.updateArea(areaDTO, areaInfoDTO, params);

        return new Result<>();
    }

    @PostMapping("import")
    @ApiOperation(value = "Excel导入")
    @LogOperation("区域信息 -导入")
    @RequiresPermissions("area:area:import")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        ImportParams params = new ImportParams();
        // 将excel转换成list
        List<AreaExcel> excelList;
        try {
            excelList = ExcelImportUtil.importExcel(file.getInputStream(), AreaExcel.class, params);
        } catch (Exception e) {
            log.error("Excel解析失败", e);
            ImportUtils.errorParseExcel(response);
            return;
        }

        // 当导入Excel中不存在数据时
        if (excelList.size() == 0) {
            ImportUtils.noDataImport(response);
            return;
        }

        // 执行导入逻辑操作
        List<AreaExcel> errorList = areaService.importExcel(excelList);

        if (errorList.size() > 0) {
            String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            String fileName = "区域导入错误信息-" + dataStr;

            // 导入的Excel中存在错误信息，下载错误数据
            ImportUtils.errorImport(excelList.size(), response, errorList, AreaExcel.class, fileName);
        } else {
            // 导入成功
            ImportUtils.successImport(excelList.size(), response);
        }
    }

    @GetMapping("buildingAndFloorTree")
    @ApiOperation("楼栋楼层数")
//    @RequiresPermissions("area:area:page")
    public Result<List<AreaTreeData>> buildingAndFloorTree() {
        //需要查询的区域类型
        String areaType = Constant.AreaType.FLOOR.getValue() + "," + Constant.AreaType.BULIDING.getValue() + "," + Constant.AreaType.VILLAGE.getValue();
        List<AreaTreeData> areaTreeDataList = areaService.getAreaTree(areaType, null);
        return new Result<List<AreaTreeData>>().ok(areaTreeDataList);
    }

    @GetMapping("/getBuildingTree")
    @ApiOperation("获取楼栋")
    @LogOperation("获取楼栋")
    public Result<List<AreaTreeData>> getBuildingAndFloor(@RequestParam(required = true) boolean isSelectRoom) {
        if (isSelectRoom) {
            return new Result<List<AreaTreeData>>().ok(areaService.getBuildingAndFloor(Arrays.asList(Constant.AreaType.FLOOR.getValue(), Constant.AreaType.BULIDING.getValue(), Constant.AreaType.ROOM.getValue()), null));
        } else {
            return new Result<List<AreaTreeData>>().ok(areaService.getBuildingAndFloor(Arrays.asList(Constant.AreaType.FLOOR.getValue(), Constant.AreaType.BULIDING.getValue()), null));
        }
    }
}