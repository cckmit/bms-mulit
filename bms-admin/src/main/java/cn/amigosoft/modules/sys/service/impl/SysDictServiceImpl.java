/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.sys.dao.SysDictDao;
import cn.amigosoft.modules.sys.dto.SysDictDTO;
import cn.amigosoft.modules.sys.entity.SysDictEntity;
import cn.amigosoft.modules.sys.service.SysDictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.amigosoft.common.constant.Constant.DICT_ROOT;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageData<SysDictDTO> page(Map<String, Object> params) {
        QueryWrapper<SysDictEntity> wrapper = getWrapper(params);
        wrapper.eq("pid", Constant.DICT_ROOT);

        IPage<SysDictEntity> page = baseDao.selectPage(
                getPage(params, "sort", true),
                wrapper
        );

        return getPageData(page, SysDictDTO.class);
    }

    @Override
    public List<SysDictDTO> list(Map<String, Object> params) {
        QueryWrapper qw = new QueryWrapper();
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");
        String dictValue = (String) params.get("dictValue");

        qw.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        qw.eq(StringUtils.isNotBlank(dictName), "dict_name", dictName);
        qw.eq(StringUtils.isNotBlank(dictValue), "dict_value", dictValue);
        qw.ne("pid", 0);
        qw.orderByAsc("sort");
        List<SysDictEntity> entityList = baseDao.selectList(qw);

        return ConvertUtils.sourceToTarget(entityList, SysDictDTO.class);
    }

    private QueryWrapper<SysDictEntity> getWrapper(Map<String, Object> params) {
        String pid = (String) params.get("pid");
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");
        String dictValue = (String) params.get("dictValue");
        String remark = (String) params.get("remark");
        String createStartDate = (String) params.get("createStartDate");
        String createEndDate = (String) params.get("createEndDate");

        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(pid), "pid", Long.parseLong(pid));
        wrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        wrapper.like(StringUtils.isNotBlank(dictName), "dict_name", dictName);
        wrapper.like(StringUtils.isNotBlank(dictValue), "dict_value", dictValue);
        wrapper.like(StringUtils.isNotBlank(remark), "remark", remark);
        wrapper.ge(StringUtils.isNotBlank(createStartDate), "create_date", createStartDate);
        //wrapper.le(StringUtils.isNotBlank(createEndDate),"create_date", createEndDate);
        if (StringUtils.isNotBlank(createEndDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            try {
                Date date = sdf.parse(createEndDate);
                calendar.setTime(date);
                calendar.add(calendar.DATE, 1);
                String endDate = sdf.format(calendar.getTime());
                wrapper.lt("create_date", endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return wrapper;
    }

    @Override
    public SysDictDTO get(Long id) {
        SysDictEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        updateById(entity);

        //修改字典值的类型
        if (entity.getPid() == DICT_ROOT.longValue()) {
            baseDao.updateDictType(entity.getDictType(), entity.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<SysDictDTO> listByDictType(String dictType) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("dict_type", dictType);
        qw.ne("pid", 0);
        qw.orderByAsc("sort");
        List<SysDictEntity> entityList = baseDao.selectList(qw);
        return ConvertUtils.sourceToTarget(entityList, SysDictDTO.class);
    }

}