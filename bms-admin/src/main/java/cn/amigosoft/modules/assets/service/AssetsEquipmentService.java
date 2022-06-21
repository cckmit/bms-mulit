package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.assets.dto.*;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentEntity;
import cn.amigosoft.modules.assets.excel.AssetsEquipmentImportExcel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
public interface AssetsEquipmentService extends CrudService<AssetsEquipmentEntity, AssetsEquipmentDTO> {
    //资本台账列表查询
    PageData<AssetsEquipmentDTO> getAssetsPage(Map<String, Object> params);

    //获取资产详情
    AssetsEquipmentDTO getDetail(Long id);

    //获取维修记录
    PageData<AssetsRepairDTO> getRepairRecord(Map<String, Object> params);

    //获取资产导出列表
    List<AssetsEquipmentDTO> getExportList(Map<String, Object> params);

    Integer countByAssetsEquipmentTypeId(Long assetsEquipmentTypeId);

    //获取资产设备接口,用于资产领用分配
    PageData<AssetsEquipmentDTO> getEquipmentPage(Map<String, Object> params);


    //获取物联网设备导出列表
    List<AssetsEquipmentDTO> getIotExportList(Map<String, Object> params);

    //获取列表树选择数据
    List<AssetsTypeTreeData> getAssetsTypeTree();

    //获取固定资产列表树选择数据
    List<AssetsTypeTreeData> getAssetsTypeTreeGd();

    //获取新增选择树（固定与通用）
    List<AssetsTypeTreeData> getAssetsAddTypeTree(Map<String, Object> params);

    //物联网设备台账
    PageData<AssetsEquipmentDTO> getIotPage(Map<String, Object> params);

    PageData<AssetsEquipmentDTO> getAssetsOnPlan(Map<String, Object> params);

    PageData<AssetsEquipmentDTO> getAssetsIotOnPlan(Map<String, Object> params);

    //获取物联网设备详情
    AssetsEquipmentDTO getIotDetail(Long id);

    void saveAssets(AssetsEquipmentDTO dto);

    void updateAssets(AssetsEquipmentDTO dto);

    // 生成二维码标签
    void setQrCodeUrl(AssetsEquipmentDTO dto);
    // 下载二维码标签
    void downloadQRCodeZip(Map<String, Object> params, HttpServletResponse response, HttpServletRequest request);

    List<AssetsEquipmentDTO> getExpireEquipmentList();

    void checkExcelDate(List<AssetsEquipmentImportExcel> excelDate, List<AssetsEquipmentImportExcel> excelErrorDate);

    //获取通用设备的使用状态
    AssetsEquipmentDTO getStatus(Long id);

}