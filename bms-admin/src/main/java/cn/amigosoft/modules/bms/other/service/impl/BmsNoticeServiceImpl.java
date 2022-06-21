package cn.amigosoft.modules.bms.other.service.impl;

import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.other.dao.BmsNoticeDao;
import cn.amigosoft.modules.bms.other.dto.BmsNoticeDTO;
import cn.amigosoft.modules.bms.other.entity.BmsNoticeEntity;
import cn.amigosoft.modules.bms.other.service.BmsNoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通知表
 */
@Service
public class BmsNoticeServiceImpl extends CrudServiceImpl<BmsNoticeDao, BmsNoticeEntity, BmsNoticeDTO> implements BmsNoticeService {

    @Override
    public QueryWrapper<BmsNoticeEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsNoticeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        String status = (String) params.get("status");
        if (StringUtils.isNotEmpty(status) && (!BmsConstant.EMPTY_ID.toString().equals(status))) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("sort");
        return wrapper;
    }


}