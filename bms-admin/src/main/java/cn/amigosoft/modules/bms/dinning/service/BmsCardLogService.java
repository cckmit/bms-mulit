package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.bms.dinning.dto.BmsCardLogDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsCardLogEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 一卡通记录表 
 */
public interface BmsCardLogService extends CrudService<BmsCardLogEntity, BmsCardLogDTO> {

    void importCsv(MultipartFile file, HttpServletResponse response) throws Exception;

}