package cn.amigosoft.modules.notice.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.notice.dto.BmsNoticeDTO;
import cn.amigosoft.modules.notice.entity.BmsNoticeEntity;

import java.util.List;

/**
 * 通知表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-21
 */
public interface BmsNoticeService extends CrudService<BmsNoticeEntity, BmsNoticeDTO> {

    // 获取通知列表
    List<BmsNoticeDTO> getNoticeList();
}