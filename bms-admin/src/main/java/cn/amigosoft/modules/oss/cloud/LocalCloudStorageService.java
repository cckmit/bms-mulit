/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.oss.cloud;

import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 本地上传
 *
 * @author Mark sunlightcs@gmail.com
 */
public class LocalCloudStorageService extends AbstractCloudStorageService {

    public LocalCloudStorageService(CloudStorageConfig config){
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path,String contentType) {
        return upload(new ByteArrayInputStream(data), path,contentType);
    }

    @Override
    public String upload(InputStream inputStream, String path,String contentType) {
        String newPath = config.getLocalPath() + File.separator + path;
        File file = new File(newPath);
        try {
            FileUtils.copyToFile(inputStream, file);
        } catch (IOException e) {
            throw new RenException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }
        return config.getLocalDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix,String contentType) {
        return upload(data, getPath(config.getLocalPrefix(), suffix),contentType);
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix,String contentType) {
        return upload(inputStream, getPath(config.getLocalPrefix(), suffix),contentType);
    }
}
