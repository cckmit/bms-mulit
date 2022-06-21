package cn.amigosoft.modules.sys.service;

import cn.amigosoft.modules.sys.dto.DeptPositionDTO;
import cn.amigosoft.modules.sys.entity.DeptPositionEntity;
import cn.amigosoft.common.service.CrudService;

import java.util.List;

/**
 * 部门职位表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-17
 */
public interface DeptPositionService extends CrudService<DeptPositionEntity, DeptPositionDTO> {

    List<DeptPositionDTO> getPositionList(Long id);

}