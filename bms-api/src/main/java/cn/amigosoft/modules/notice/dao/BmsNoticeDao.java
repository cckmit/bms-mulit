package cn.amigosoft.modules.notice.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.notice.dto.BmsNoticeDTO;
import cn.amigosoft.modules.notice.entity.BmsNoticeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通知表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-21
 */
@Mapper
public interface BmsNoticeDao extends BaseDao<BmsNoticeEntity> {

    List<BmsNoticeDTO> getNoticeList();
}