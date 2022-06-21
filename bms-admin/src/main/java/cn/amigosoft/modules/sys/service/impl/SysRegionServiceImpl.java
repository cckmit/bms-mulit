/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.sys.dao.SysRegionDao;
import cn.amigosoft.modules.sys.dto.RegionDTO;
import cn.amigosoft.modules.sys.dto.SysRegionDTO;
import cn.amigosoft.modules.sys.dto.region.Region;
import cn.amigosoft.modules.sys.dto.region.RegionCity;
import cn.amigosoft.modules.sys.dto.region.RegionProvince;
import cn.amigosoft.modules.sys.entity.SysRegionEntity;
import cn.amigosoft.modules.sys.enums.RegionLeafEnum;
import cn.amigosoft.modules.sys.enums.RegionLevelEnum;
import cn.amigosoft.modules.sys.service.SysRegionService;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRegionServiceImpl extends BaseServiceImpl<SysRegionDao, SysRegionEntity> implements SysRegionService {

    @Override
    public List<SysRegionDTO> list(Map<String, Object> params) {
        String pid = (String) params.get("pid");

        if (StringUtils.isBlank(pid)) {
            //查询一级
            params.put("treeLevel", RegionLevelEnum.ONE.value());
        }
        //查询列表
        List<SysRegionEntity> entityList = baseDao.getList(params);

        List<SysRegionDTO> dtoList = new ArrayList<>(entityList.size());
        for (SysRegionEntity entity : entityList) {
            SysRegionDTO dto = new SysRegionDTO();
            BeanUtils.copyProperties(entity, dto);
            dto.setHasChildren(entity.getLeaf() != 1);

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public List<Map<String, Object>> getTreeList() {
        return baseDao.getTreeList();
    }

    @Override
    public SysRegionDTO get(Long id) {
        SysRegionEntity entity = baseDao.getById(id);

        return ConvertUtils.sourceToTarget(entity, SysRegionDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRegionDTO dto) {
        SysRegionEntity entity = ConvertUtils.sourceToTarget(dto, SysRegionEntity.class);

        //查询上级
        SysRegionEntity parentEntity = baseDao.getById(dto.getPid());
        if (parentEntity == null) {
            entity.setTreeLevel(RegionLevelEnum.ONE.value());
        } else {
            entity.setTreeLevel(parentEntity.getTreeLevel() + 1);
            //上级存在，且为叶子节点，需要修改为非叶子节点
            if (parentEntity.getLeaf() == RegionLeafEnum.YES.value()) {
                parentEntity.setLeaf(RegionLeafEnum.NO.value());
                baseDao.updateById(parentEntity);
            }
        }

        //新增都是叶子节点
        entity.setLeaf(RegionLeafEnum.YES.value());
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRegionDTO dto) {
        SysRegionEntity entity = ConvertUtils.sourceToTarget(dto, SysRegionEntity.class);

        //上级不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new RenException(ErrorCode.SUPERIOR_REGION_ERROR);
        }

        //查询上级
        SysRegionEntity parentEntity = baseDao.getById(dto.getPid());
        if (parentEntity == null) {
            entity.setTreeLevel(RegionLevelEnum.ONE.value());
        } else {
            entity.setTreeLevel(parentEntity.getTreeLevel() + 1);
            //上级存在，且为叶子节点，需要修改为非叶子节点
            if (parentEntity.getLeaf() == RegionLeafEnum.YES.value()) {
                parentEntity.setLeaf(RegionLeafEnum.NO.value());
                baseDao.updateById(parentEntity);
            }
        }

        //查询下级
        int subCount = baseDao.getCountByPid(dto.getId());
        if (subCount == 0) {
            entity.setLeaf(RegionLeafEnum.YES.value());
        } else {
            entity.setLeaf(RegionLeafEnum.NO.value());
        }

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //删除
        baseDao.deleteById(id);
    }

    @Override
    public int getCountByPid(Long pid) {
        return baseDao.getCountByPid(pid);
    }

    @Override
    public List<RegionProvince> getRegion(boolean threeLevel) {
        List<SysRegionEntity> provinceList = baseDao.getListByLevel(RegionLevelEnum.ONE.value());
        List<SysRegionEntity> cityList = baseDao.getListByLevel(RegionLevelEnum.TWO.value());

        List<RegionProvince> provinces = ConvertUtils.sourceToTarget(provinceList, RegionProvince.class);
        List<RegionCity> cities = ConvertUtils.sourceToTarget(cityList, RegionCity.class);

        for (RegionCity city : cities) {
            for (RegionProvince province : provinces) {
                if (city.getPid().equals(province.getId())) {
                    province.getCities().add(city);
                }
            }
        }

        //无需显示3级区县
        if (!threeLevel) {
            return provinces;
        }

        List<SysRegionEntity> countyList = baseDao.getListByLevel(RegionLevelEnum.THREE.value());
        List<Region> counties = ConvertUtils.sourceToTarget(countyList, Region.class);
        for (Region county : counties) {
            for (RegionCity city : cities) {
                if (county.getPid().equals(city.getId())) {
                    city.getCounties().add(county);
                }
            }
        }

        return provinces;
    }

    @Override
    public RegionDTO getRegionName(String code) {
        Map<String, Object> map = new HashMap<String, Object>(3);
        String[] area = code.split(",");
        if (area.length == 0) {
            return new RegionDTO();
        }
        //	存在省
        map.put("province", area[0]);
        //	存在市
        map.put("city", area.length > 1 ? area[1] : null);
        //	存在县
        map.put("county", area.length > 2 ? area[2] : null);
        return baseDao.selectRegionName(map);
    }

    @Override
    public List<SysRegionEntity> getRegionMap() {
        QueryWrapper<SysRegionEntity> wrapper = new QueryWrapper<>();
        List<SysRegionEntity> regionList = this.baseDao.selectList(wrapper);
        return regionList;
    }

    /***
     * 根据省市名称查询对应id，id使用#号隔开
     * @param provience 省
     * @param city 市
     * @param county 县
     * @return
     */
    @Override
    public String queryProvienceCityCountyId(String provience, String city, String county) {
        return this.baseDao.queryProvienceCityCountyId(provience, city, county);
    }
}
