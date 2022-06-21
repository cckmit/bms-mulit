package cn.amigosoft.modules.bms.repair.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.TreeUtils;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsTypeDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsTypeDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsTypeEntity;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产类别表
 */
@Service
public class BmsAssetsTypeServiceImpl extends CrudServiceImpl<BmsAssetsTypeDao, BmsAssetsTypeEntity, BmsAssetsTypeDTO> implements BmsAssetsTypeService {

    @Override
    public QueryWrapper<BmsAssetsTypeEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsAssetsTypeEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public List<BmsAssetsTypeDTO> selectAssetsTypeList() {
        QueryWrapper<BmsAssetsTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        List<BmsAssetsTypeEntity> list = baseDao.selectList(queryWrapper);
        List<BmsAssetsTypeDTO> dtoList = ConvertUtils.sourceToTarget(list, BmsAssetsTypeDTO.class);
        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }

    @Override
    public List<BmsAssetsTypeDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public List<BmsAssetsTypeDTO> list(Map<String, Object> params) {
        List<BmsAssetsTypeDTO> list = super.list(params);
        return TreeUtils.build(list);
    }
}