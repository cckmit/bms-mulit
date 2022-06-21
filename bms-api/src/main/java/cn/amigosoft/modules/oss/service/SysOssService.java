/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.oss.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.BaseService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysOssService extends BaseService<SysOssEntity> {

    PageData<SysOssEntity> page(Map<String, Object> params);

    /**
     * 根据图片base64字符串上传图片
     *
     * @param fileBase64String
     * @return
     * @throws Exception
     */
    Result<Map<String, Object>> uploadByString(String fileBase64String, String extension, String contentType) throws Exception;

    /**
     * 根据图片的字节数组来上传图片
     *
     * @param fileBytes
     * @param extension
     * @return
     * @throws Exception
     */
    Result<Map<String, Object>> uploadByBytes(byte[] fileBytes, String extension, String contentType) throws Exception;
}
