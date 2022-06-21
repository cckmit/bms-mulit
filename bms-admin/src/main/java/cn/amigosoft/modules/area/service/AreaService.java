package cn.amigosoft.modules.area.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.area.dto.AreaBriefInfoDTO;
import cn.amigosoft.modules.area.dto.AreaDTO;
import cn.amigosoft.modules.area.dto.AreaTreeData;
import cn.amigosoft.modules.area.dto.TreeData;
import cn.amigosoft.modules.area.entity.AreaEntity;
import cn.amigosoft.modules.area.excel.AreaExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Map;

/**
 * 设施信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
public interface AreaService extends CrudService<AreaEntity, AreaDTO> {

    /**
     * @Describe: areaId 区域id
     * @Required: areaName 区域中的名称
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/18 18:43
     */
    PageData<AreaBriefInfoDTO> query(Map<String, Object> params);

    /**
     * @Describe: 按照树形查询设施信息
     * @Required: pid, identity是否包含本身节点，默认true
     * @Optional: areaType 指定树形二级节点类型
     * @Author: ChenXiong
     * @Date: 2020/7/10 14:59
     */
    List<TreeData> queryTreeData(Long pid, boolean identity, String areaType);

    /**
     * @Describe: 按照list查询 楼栋|楼层|房屋 信息
     * @Required: pid, facilityType 节点类型
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/15 16:53
     */
    List<TreeData> queryBuildingFloorRoomData(Long pid, int facilityType);

    /**
     * @Describe: 查询 areaId 节点下的设施信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:10
     */
    List<TreeData> queryFacilityData(long areaId);

    /**
     * 查询出指定的区域树
     *
     * @param typeStr 要排除的查询类型
     * @param del     是否删除
     * @param pid     上级id
     * @param type    要筛选的类型
     * @param senceos 要筛选的场景模型
     * @return
     */
    List<AreaTreeData> queryAllByType(String typeStr, Integer del, Long pid, Integer type, String senceos);

    /***
     * @Describe: 社区信息导入
     * @Required: file Excel文件
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:05
     */
    List<AreaExcel> importExcel(List<AreaExcel> excelList);

    /***
     * @Describe: 根据类型快速添加社区、楼栋、楼层、房屋信息，仅添加area表数据和对应详细信息表中必要数据
     * @Required: areaName 名称, areaType 节点类型, pid 父节点的 areaId
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:06
     */
    Result<Object> insertOnly(String areaName, Integer areaType, Long pid);

    /**
     * 使用查询器 LambdaQueryWrapper 查询 list
     */
    List<AreaEntity> selectList(LambdaQueryWrapper<AreaEntity> queryWrapper);

    /**
     * @Describe: 根据 areaNames 名称查询最末级节点信息
     * @Required: areaNames 由名称拼接成，例如：天翼智慧社区,停车场一,停车场一停车位1
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:12
     */
    List<AreaDTO> getByAreaNames(String areaNames);

    /**
     * @Describe: 根据 areaNames 名称查询最末级节点信息
     * @Required: areaNames 由名称拼接成，例如：天翼智慧社区,停车场一,停车场一停车位1,层级新增汉字层
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:12
     */
    List<AreaDTO> getByAreaNamesByDeviceImport(String areaNames);

    /**
     * @Describe: 查询一个area节点下包含的住户数量
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/8/7 15:34
     */
    Integer queryResidentCount(Long areaId);

    /***
     * 查询当前用户社区id
     * @return
     */
    Result<Long> getAaeaIdByUser();

    String getAddressByBuildingAreaId(Long areaId);

    List<AreaDTO> getTopArea();

    String selectAreaNameById(Long areaId);

    List<AreaTreeData> getBuildingAndFloor(List<Integer> areaType, Long buildingId);

    List<Long> getFloorIdsByBuildingId(Long buildingId);

    List<AreaEntity> getByAreaType(Integer value);

    void updateScenesById(String scenes, Long areaId);

    /**
     * 获取区域树
     *
     * @param areaType     要查询的数据类型
     * @param scenesIsBind 区域场景是否绑定
     * @return
     */
    List<AreaTreeData> getAreaTree(String areaType, Integer scenesIsBind);

    /***
     * 查询区域详情
     * @return
     */
    AreaEntity getAreaInfo(Long areaId);

    void updateScenesByIds(String scenes, List<Long> areaIds, Long tenantId);

    /**
     * 根据ids查询区域列表
     *
     * @param dutyAreaIds
     * @return
     */
    List<AreaDTO> getAreaListByIds(List<Long> dutyAreaIds);

}