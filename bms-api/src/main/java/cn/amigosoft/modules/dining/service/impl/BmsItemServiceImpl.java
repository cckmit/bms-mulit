package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.dining.dao.BmsItemDao;
import cn.amigosoft.modules.dining.dto.BmsItemDTO;
import cn.amigosoft.modules.dining.entity.BmsItemEntity;
import cn.amigosoft.modules.dining.service.BmsItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 餐品表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Service
public class BmsItemServiceImpl extends CrudServiceImpl<BmsItemDao, BmsItemEntity, BmsItemDTO> implements BmsItemService {

    @Override
    public QueryWrapper<BmsItemEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsItemEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public List<BmsItemDTO> selectItemList(Map<String, Object> params) {
        return baseDao.selectItemList(params);
    }

    @Override
    public BmsItemDTO selectItemById(Long id) {
        BmsItemDTO dto = baseDao.selectItemById(id);
        List<String> list = baseDao.selectDishesImage(id);
        boolean empty = true;
        for (int i = 0; i < list.size(); i++) {
            if (!StringUtils.isBlank(list.get(i))) {
                empty = false;
                break;
            }
        }
        if (empty == false) {
            dto.setDishesImgList(list);
        } else {
            dto.setDishesImgList(null);
        }
        return dto;
    }

    @Override
    public List<BmsItemDTO> selectItemByIds(Long[] ids) {
        return baseDao.selectItemByIds(ids);
    }

    @Override
    public List<BmsItemDTO> getItemList() {
        return baseDao.getItemList();
    }
}