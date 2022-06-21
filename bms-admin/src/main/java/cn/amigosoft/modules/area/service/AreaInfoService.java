package cn.amigosoft.modules.area.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.area.dto.AreaCountInfoDTO;
import cn.amigosoft.modules.area.dto.AreaDTO;
import cn.amigosoft.modules.area.dto.AreaDetailInfoDTO;
import cn.amigosoft.modules.area.dto.AreaInfoDTO;
import cn.amigosoft.modules.area.entity.AreaInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 区域信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
public interface AreaInfoService extends CrudService<AreaInfoEntity, AreaInfoDTO> {

    /**
     * @Describe: 查询区域列表信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/11 16:13
     */
    List<AreaInfoDTO> queryAreaList();

    AreaDetailInfoDTO queryAreaInfo(long areaId);

    /**
     * @Describe: 更新社区信息
     * @Author: ChenXiong
     * @Date: 2020/8/26 15:32
     */
    void updateArea(AreaDTO areaDTO, AreaInfoDTO areaInfoDTO, Map<String, Object> params);

    /**
     * @Describe: 根据区域在area表中的id查询area_info表信息
     * @Required: areaId 区域在area表中的id
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/19 16:53
     */
    AreaInfoEntity selectByAreaId(long areaId);

    void updateBuildingNumByBuildingFacilityId(Long facilityId);

    /**
     * @param areaId
     * @Describe: 获取小区的文字地址信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 15:54
     */
    String getAreaAddress(Long areaId);

    AreaCountInfoDTO queryAreaCountInfo(Long areaId);

    AreaInfoDTO getAreaInfoByTenant(Long tenantId);
}