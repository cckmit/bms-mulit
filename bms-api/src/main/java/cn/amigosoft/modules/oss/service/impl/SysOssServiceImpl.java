/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.oss.service.impl;

import cn.amigosoft.modules.oss.service.SysOssService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import cn.amigosoft.modules.oss.dao.SysOssDao;
import cn.amigosoft.modules.oss.entity.SysOssEntity;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageData<SysOssEntity> page(Map<String, Object> params) {
        IPage<SysOssEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                new QueryWrapper<>()
        );
        return getPageData(page, SysOssEntity.class);
    }

    /**
     * 根据图片base64字符串上传图片
     *
     * @param fileBase64String
     * @return
     * @throws Exception
     */
    @Override
    public Result<Map<String, Object>> uploadByString(String fileBase64String, String extension, String contentType) throws Exception {
        if (fileBase64String.isEmpty()) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        //上传文件
        String url = OSSFactory.build().uploadSuffix(new BASE64Decoder().decodeBuffer(fileBase64String), extension, contentType);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        baseDao.insert(ossEntity);

        Map<String, Object> data = new HashMap<>(1);
        data.put("src", url);

        return new Result<Map<String, Object>>().ok(data);
    }

    /**
     * 根据图片的字节数组来上传图片
     *
     * @param fileBytes
     * @param extension
     * @return
     * @throws Exception
     */
    public Result<Map<String, Object>> uploadByBytes(byte[] fileBytes, String extension, String contentType) throws Exception {
        if (null == fileBytes || 0 == fileBytes.length) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        //上传文件
        String url = OSSFactory.build().uploadSuffix(fileBytes, extension, contentType);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        baseDao.insert(ossEntity);

        Map<String, Object> data = new HashMap<>(1);
        data.put("src", url);

        return new Result<Map<String, Object>>().ok(data);
    }

}
