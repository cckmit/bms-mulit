package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.dining.entity.BmsScanVerificationEntity;

/**
 * 扫码核销表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
public interface BmsScanVerificationService extends CrudService<BmsScanVerificationEntity, BmsScanVerificationDTO> {

    //新增扫码核销记录
    Result addScanVerification(BmsScanVerificationDTO dto);
}