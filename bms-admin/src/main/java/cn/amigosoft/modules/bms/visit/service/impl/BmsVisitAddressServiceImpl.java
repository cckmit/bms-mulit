package cn.amigosoft.modules.bms.visit.service.impl;

import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitAddressDao;
import cn.amigosoft.modules.bms.visit.dao.BmsVisitAddressUserDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressUserDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressEntity;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressUserEntity;
import cn.amigosoft.modules.bms.visit.service.BmsVisitAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 访问地点表
 */
@Service
public class BmsVisitAddressServiceImpl extends CrudServiceImpl<BmsVisitAddressDao, BmsVisitAddressEntity, BmsVisitAddressDTO> implements BmsVisitAddressService {

    @Autowired
    private BmsVisitAddressUserDao visitAddressUserDao;

    @Override
    public QueryWrapper<BmsVisitAddressEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");
        QueryWrapper<BmsVisitAddressEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        return wrapper;
    }


    @Override
    public BmsVisitAddressDTO selectAddressById(Long id) {
        BmsVisitAddressEntity entity = baseDao.selectById(id);
        BmsVisitAddressDTO dto = ConvertUtils.sourceToTarget(entity, BmsVisitAddressDTO.class);
        List<BmsVisitAddressUserDTO> list = visitAddressUserDao.selectByAddressId(id);
        dto.setVerifyUserList(list);
        return dto;
    }

    @Override
    public Result insertAddress(BmsVisitAddressDTO dto) {
        BmsVisitAddressEntity addressEntity = new BmsVisitAddressEntity();
        addressEntity.setName(dto.getName());
        addressEntity.setRemark(dto.getRemark());
        baseDao.insert(addressEntity);
        Long id = addressEntity.getId();
        List<BmsVisitAddressUserDTO> list = dto.getVerifyUserList();
        BmsVisitAddressUserEntity addressUserEntity = new BmsVisitAddressUserEntity();
        addressUserEntity.setAddressId(id);
        addressUserEntity.setUserId(list.get(0).getUserId());
        visitAddressUserDao.insert(addressUserEntity);
        return new Result();
    }

    @Override
    public Result updateAddress(BmsVisitAddressDTO dto) {
        Long id = dto.getId();
        QueryWrapper<BmsVisitAddressUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address_id", id);
        visitAddressUserDao.delete(queryWrapper);
        List<BmsVisitAddressUserDTO> list = dto.getVerifyUserList();
        BmsVisitAddressEntity entity = ConvertUtils.sourceToTarget(dto, BmsVisitAddressEntity.class);
        baseDao.updateById(entity);
        BmsVisitAddressUserEntity addressUserEntity = new BmsVisitAddressUserEntity();
        addressUserEntity.setAddressId(id);
        addressUserEntity.setUserId(list.get(0).getUserId());
        visitAddressUserDao.insert(addressUserEntity);
        return new Result();
    }
}
