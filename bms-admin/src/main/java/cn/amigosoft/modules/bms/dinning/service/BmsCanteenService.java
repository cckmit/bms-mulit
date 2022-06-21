package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.bms.dinning.dto.BmsCanteenDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsCanteenEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 食堂表 
 */
public interface BmsCanteenService extends CrudService<BmsCanteenEntity, BmsCanteenDTO> {

    /**
     * 通过ID查询食堂信息
     */
    BmsCanteenDTO selectCanteenById(Long id);

    List<BmsCanteenDTO> export(Map<String, Object> params);

    List<BmsCanteenDTO> baseInfoList();

    void downloadQRCodeZip(Map<String, Object> params, HttpServletResponse response, HttpServletRequest request);
}