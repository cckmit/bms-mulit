package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.service.BmsOrderDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsScanVerificationDao;
import cn.amigosoft.modules.dining.dto.BmsScanVerificationDTO;
import cn.amigosoft.modules.dining.entity.BmsScanVerificationEntity;
import cn.amigosoft.modules.dining.service.BmsScanVerificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 扫码核销表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@Service
public class BmsScanVerificationServiceImpl extends CrudServiceImpl<BmsScanVerificationDao, BmsScanVerificationEntity, BmsScanVerificationDTO> implements BmsScanVerificationService {

    @Autowired
    private BmsOrderDetailService orderDetailService;

    @Override
    public QueryWrapper<BmsScanVerificationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsScanVerificationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public Result addScanVerification(BmsScanVerificationDTO dto) {
        Long orderDetailId = dto.getOrderDetailId();
        QueryWrapper<BmsScanVerificationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_detail_id", orderDetailId);
        Integer count = baseDao.selectCount(wrapper);
        if (count > 0) {
            return new Result().error("该餐次已核销");
        }
        BmsScanVerificationEntity entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        baseDao.insert(entity);
        BmsOrderDetailDTO orderDetailDTO = new BmsOrderDetailDTO();
        orderDetailDTO.setId(orderDetailId);
        orderDetailDTO.setScanStatus(BmsConstant.ORDER_PAID);
        orderDetailService.update(orderDetailDTO);
        return new Result();
    }
}