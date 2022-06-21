/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.oss.cloud;

import com.aliyun.oss.OSSClient;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 *
 * @author Mark sunlightcs@gmail.com
 */
public class AliyunCloudStorageService extends AbstractCloudStorageService {

    public AliyunCloudStorageService(CloudStorageConfig config){
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path, String contentType) {
        return upload(new ByteArrayInputStream(data), path,contentType);
    }

    @Override
    public String upload(InputStream inputStream, String path, String contentType) {
        OSSClient client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
        try {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
            client.shutdown();
        } catch (Exception e){
            throw new RenException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }

        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix, String contentType) {
        return upload(data, getPath(config.getAliyunPrefix(), suffix),contentType);
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix,String contentType) {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix),contentType);
    }
}