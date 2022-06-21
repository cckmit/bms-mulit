package cn.amigosoft.modules.area.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.area.dto.AreaBriefInfoDTO;
import cn.amigosoft.modules.area.dto.AreaDTO;
import cn.amigosoft.modules.area.dto.AreaTreeData;
import cn.amigosoft.modules.area.dto.TreeData;
import cn.amigosoft.modules.area.entity.AreaEntity;
import com.baomidou.mybatisplus.annotation.SqlParser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 设施信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
@Mapper
public interface AreaDao extends BaseDao<AreaEntity> {

    /**
     * @Describe: areaId 区域id
     * @Required: areaName 区域中的名称
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/18 18:43
     */
    List<AreaBriefInfoDTO> query(Map<String, Object> params);

    /***
     * @Describe: 查询设施信息id, pid, name, type 在上层service处理成树形结构
     * @Author: ChenXiong
     * @Date: 2020/7/10 15:05
     */
    List<TreeData> queryFacilityData();

    /**
     * 指定类型的区域
     *
     * @param typeStr
     * @param del
     * @return
     */
    List<AreaTreeData> queryAllByType(@Param("typeStr") String typeStr, @Param("del") Integer del);

    /**
     * @Describe: 根据 pnams 名称查询最末级节点信息
     * @Required: areaNames 由名称拼接成，例如：天翼智慧社区,停车场一,停车场一停车位1
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 11:12
     */
    List<AreaDTO> getByAreaNames(@Param("areaNames") String areaNames);

    List<AreaDTO> getByAreaNamesByDeviceImport(@Param("areaNames") String areaNames);

    String getAddressByBuildingAreaId(Long areaId);

    List<AreaDTO> getTopAreaByAreaName(String areaName);

    List<AreaDTO> getTopArea();

    List<AreaEntity> getByAreaType(@Param("areaType") Integer areaType);

    void updateScenesById(@Param("scenes") String scenes, @Param("areaId") Long areaId);

    @SqlParser(filter = true)
    List<AreaTreeData> getAreaTree(@Param("areaType") String areaType, @Param("tenantId") Long tenantId, @Param("scenesIsBind") Integer scenesIsBind);

    List<AreaEntity> selectListByPidAndType(@Param("areaId") Long areaId);

    void updateScenesByIds(@Param("scenes") String scenes, @Param("areaIds") List<Long> areaIds, @Param("tenantId") Long tenantId);
}