package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.*;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@Mapper
public interface AssetsEquipmentDao extends BaseDao<AssetsEquipmentEntity> {
    //资产台账信息查询
    List<AssetsEquipmentDTO> getAssetsPage(Map<String, Object> params);

    //资产详情查询
    AssetsEquipmentDTO getDetail(@Param("id") Long id);

    //维修记录
    List<AssetsRepairDTO> getRepairRecord(Map<String, Object> params);

    //获取导出列表
    List<AssetsEquipmentDTO> getExportList(Map<String, Object> params);

    Integer countByAssetsEquipmentTypeId(@Param("typeId") Long typeId);

    List<AssetsEquipmentDTO> getEquipmentPage(IPage<AssetsEquipmentEntity> page, @Param("params") Map<String, Object> params);

    //物联网设备列表查询
    List<AssetsEquipmentDTO> getIotPage(Map<String, Object> params);

    //物联网设备详情
    AssetsEquipmentDTO getIotDetail(@Param("id") Long id);

    //获取物联网设备导出列表
    List<AssetsEquipmentDTO> getIotExportList(Map<String, Object> params);

    //获取树选择数据
    List<AssetsTypeTreeData> getAssetsTypeTree(@Param("tenantId") Long tenantId);

    //获取树选择数据
    List<AssetsTypeTreeData> getAssetsTypeTreeGd(@Param("tenantId") Long tenantId);

    //获取新增所需选择树（固定与通用）
    List<AssetsTypeTreeData> getAssetsAddTypeTree(Map<String, Object> params);

    // 获取需要发短信的、即将到期的资产
    List<AssetsEquipmentDTO> getExpireEquipmentList();

    List<AssetsEquipmentDTO> selectAssetsByParamsOnPlan(Map<String, Object> params);

    List<AssetsEquipmentDTO> selectAssetsIotByParamsOnPlan(Map<String, Object> params);

    List<AssetsPidNames> getPidNamesByAssetsType(@Param("tenantId") Long tenantId);

    List<AssetsPidNames> getPidNamesByDept();

    List<AssetsPidNames> getPidNamesByArea();

    AssetsEquipmentDTO getMaxNumByAssets();

    AssetsPidNames getAssetsTypesById(@Param("id") Long id);

    AssetsEquipmentDTO getAreaName(@Param("id") Long id);

    AssetsEquipmentDTO getStatus(@Param("id") Long id);

}