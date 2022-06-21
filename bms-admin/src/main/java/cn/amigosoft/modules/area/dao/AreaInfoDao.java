package cn.amigosoft.modules.area.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.area.dto.AreaCountInfoDTO;
import cn.amigosoft.modules.area.dto.AreaInfoDTO;
import cn.amigosoft.modules.area.entity.AreaInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 区域详细信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Mapper
public interface AreaInfoDao extends BaseDao<AreaInfoEntity> {
    /**
     * @Describe: 根据楼栋 facilityId 更新区域楼栋数
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/17 11:12
     */
    void updateBuildingNumByBuildingFacilityId(Long buildingFacilityId);

    /**
     * @param areaId
     * @Describe: 获取小区的文字地址信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/24 15:54
     */
    String getAreaAddress(Long areaId);

    /**
     * @Describe: 查询社区楼栋、楼层、房间、停车位等等数量信息
     * @Required: areaId 小区的areaId
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/8/1 15:50
     */
    AreaCountInfoDTO queryAreaCountInfo(Long areaId);

    AreaInfoDTO getAreaInfoByTenant(Long tenantId);

}