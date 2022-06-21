package cn.amigosoft.modules.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.notice.dao.BmsNoticeDao;
import cn.amigosoft.modules.notice.dto.BmsNoticeDTO;
import cn.amigosoft.modules.notice.entity.BmsNoticeEntity;
import cn.amigosoft.modules.notice.service.BmsNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通知表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-21
 */
@Service
public class BmsNoticeServiceImpl extends CrudServiceImpl<BmsNoticeDao, BmsNoticeEntity, BmsNoticeDTO> implements BmsNoticeService {

    @Override
    public QueryWrapper<BmsNoticeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsNoticeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    /**
     * 获取通知列表
     */
    @Override
    public List<BmsNoticeDTO> getNoticeList() {
        List<BmsNoticeDTO> list = baseDao.getNoticeList();
        return list;
    }
}