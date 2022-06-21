package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.bms.dinning.dto.BmsHolidayDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsHolidayEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 节假日表
 */
public interface BmsHolidayService extends CrudService<BmsHolidayEntity, BmsHolidayDTO> {

    void importExcel(MultipartFile file, HttpServletResponse response);

}
