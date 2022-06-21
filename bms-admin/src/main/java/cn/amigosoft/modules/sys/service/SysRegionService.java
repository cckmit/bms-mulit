/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service;

import cn.amigosoft.modules.sys.dto.RegionDTO;
import cn.amigosoft.modules.sys.dto.SysRegionDTO;
import cn.amigosoft.modules.sys.dto.region.RegionProvince;
import cn.amigosoft.modules.sys.entity.SysRegionEntity;
import cn.amigosoft.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRegionService extends BaseService<SysRegionEntity> {

    List<SysRegionDTO> list(Map<String, Object> params);

    List<Map<String, Object>> getTreeList();

    SysRegionDTO get(Long id);

    void save(SysRegionDTO dto);

    void update(SysRegionDTO dto);

    void delete(Long id);

    int getCountByPid(Long pid);

    List<RegionProvince> getRegion(boolean threeLevel);

    RegionDTO getRegionName(String code);

    List<SysRegionEntity> getRegionMap();

    /***
     * 根据省市名称查询对应id，id使用#号隔开
     * @param provience 省
     * @param city 市
     * @param county 县
     * @return
     */
    String queryProvienceCityCountyId(String provience, String city, String county);

}
