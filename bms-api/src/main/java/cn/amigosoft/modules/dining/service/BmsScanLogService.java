package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsScanLogDTO;
import cn.amigosoft.modules.dining.entity.BmsScanLogEntity;

/**
 * 扫码记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
public interface BmsScanLogService extends CrudService<BmsScanLogEntity, BmsScanLogDTO> {

    //新增扫码记录
    BmsScanLogDTO addScanLog(BmsScanLogDTO dto);
}