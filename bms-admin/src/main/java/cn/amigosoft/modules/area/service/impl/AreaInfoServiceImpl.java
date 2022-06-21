package cn.amigosoft.modules.area.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.area.dao.AreaInfoDao;
import cn.amigosoft.modules.area.dto.AreaCountInfoDTO;
import cn.amigosoft.modules.area.dto.AreaDTO;
import cn.amigosoft.modules.area.dto.AreaDetailInfoDTO;
import cn.amigosoft.modules.area.dto.AreaInfoDTO;
import cn.amigosoft.modules.area.entity.AreaInfoEntity;
import cn.amigosoft.modules.area.service.AreaInfoService;
import cn.amigosoft.modules.area.service.AreaService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 区域信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
@Service
public class AreaInfoServiceImpl extends CrudServiceImpl<AreaInfoDao, AreaInfoEntity, AreaInfoDTO> implements AreaInfoService {
    @Autowired
    private AreaService areaService;

    @Override
    public QueryWrapper<AreaInfoEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AreaInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    /**
     * @Describe: 查询区域列表信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/11 16:13
     */
    @Override
    public List<AreaInfoDTO> queryAreaList() {
        List<AreaInfoEntity> areaEntityList = this.baseDao.selectList(null);
        List<AreaInfoDTO> result = new ArrayList<>();

        for (AreaInfoEntity areaEntity : areaEntityList) {
            AreaInfoDTO areaDTO = new AreaInfoDTO();
            BeanUtils.copyProperties(areaEntity, areaDTO);
            result.add(areaDTO);
        }

        return result;
    }

    @Override
    public AreaDetailInfoDTO queryAreaInfo(long areaId) {
        // 查询社区楼栋、楼层、房间、停车位等等数量信息
        AreaCountInfoDTO areaCountInfoDto = baseDao.queryAreaCountInfo(areaId);

        AreaDetailInfoDTO areaDetailInfoDTO = new AreaDetailInfoDTO();
        BeanUtils.copyProperties(areaCountInfoDto, areaDetailInfoDTO);

        areaDetailInfoDTO.setAreaId(areaId);

        // 查询图片
        AreaDTO areaDTO = areaService.get(areaId);
        areaDetailInfoDTO.setLatitude(areaDTO.getLatitude());
        areaDetailInfoDTO.setLongitude(areaDTO.getLongitude());
        areaDetailInfoDTO.setAreaImg(areaDTO.getAreaImg());

        AreaInfoEntity areaInfo = this.selectByAreaId(areaId);
        areaDetailInfoDTO.setAreaInfoId(areaInfo.getId());
        areaDetailInfoDTO.setAreaName(areaInfo.getAreaName());

        // 社区完整地址
        String position = this.getAreaAddress(areaId);
        areaDetailInfoDTO.setPosition(position);

        // 省市县街道详细地址
        areaDetailInfoDTO.setProvinceCode(areaInfo.getProvinceCode());
        areaDetailInfoDTO.setCityCode(areaInfo.getCityCode());
        areaDetailInfoDTO.setCountyCode(areaInfo.getCountyCode());
        areaDetailInfoDTO.setStreet(areaInfo.getStreet());
        areaDetailInfoDTO.setAddress(areaInfo.getAddress());

        areaDetailInfoDTO.setStreetOfficePhone(areaInfo.getStreetOfficePhone());
        areaDetailInfoDTO.setPoliceStation(areaInfo.getPoliceStation());
        areaDetailInfoDTO.setPoliceStationPhone(areaInfo.getPoliceStationPhone());
        areaDetailInfoDTO.setDeveloper(areaInfo.getDeveloper());

        areaDetailInfoDTO.setPropertyCompanyDuty(areaInfo.getPropertyCompanyDuty());
        areaDetailInfoDTO.setPropertyCompanyName(areaInfo.getPropertyCompanyName());
        areaDetailInfoDTO.setPropertyCompanyPhone(areaInfo.getPropertyCompanyPhone());
        return areaDetailInfoDTO;
    }

    /**
     * @Describe: 更新社区信息
     * @Author: ChenXiong
     * @Date: 2020/8/26 15:32
     */
    @Override
    public void updateArea(AreaDTO areaDTO, AreaInfoDTO areaInfoDTO, Map<String, Object> params) {

        Long areaId = Long.valueOf(params.get("areaId").toString());
        areaDTO.setId(areaId);
        areaService.update(areaDTO);

        Long areaInfoId = Long.valueOf(params.get("areaInfoId").toString());
        areaInfoDTO.setId(areaInfoId);
        this.update(areaInfoDTO);
    }

    /**
     * @param areaId 区域在area表中的id
     * @Describe: 根据区域在area表中的id查询area_info表信息
     * @Required: areaId 区域在area表中的id
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/19 16:53
     */
    @Override
    public AreaInfoEntity selectByAreaId(long areaId) {
        LambdaQueryWrapper<AreaInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AreaInfoEntity::getAreaId, areaId);
        return baseDao.selectOne(queryWrapper);
    }

    @Override
    public void updateBuildingNumByBuildingFacilityId(Long facilityId) {
        baseDao.updateBuildingNumByBuildingFacilityId(facilityId);
    }

    /**
     * @param areaId
     * @Describe: 获取小区的文字地址信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 15:54
     */
    @Override
    public String getAreaAddress(Long areaId) {
        return baseDao.getAreaAddress(areaId);
    }

    @Override
    public AreaCountInfoDTO queryAreaCountInfo(Long areaId) {
        return baseDao.queryAreaCountInfo(areaId);
    }

    @Override
    public AreaInfoDTO getAreaInfoByTenant(Long tenantId) {
        return baseDao.getAreaInfoByTenant(tenantId);
    }
}