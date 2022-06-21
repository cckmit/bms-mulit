package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsScanVerificationEntity;

import java.util.List;
import java.util.Map;

/**
 * 扫码核销表
 */
public interface BmsScanVerificationService extends CrudService<BmsScanVerificationEntity, BmsScanVerificationDTO> {

    Result<PageData<BmsScanVerificationDTO>> getPage(Map<String, Object> params);

    List<BmsScanVerificationDTO> selectExportList(Map<String, Object> params);

}
