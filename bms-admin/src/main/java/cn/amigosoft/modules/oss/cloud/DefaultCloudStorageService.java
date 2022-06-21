package cn.amigosoft.modules.oss.cloud;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 说明:默认缺省云服务
 *
 * @author zhaoyg
 * @date 2021/6/8
 */
@Slf4j
@SuppressWarnings("all")
public class DefaultCloudStorageService extends AbstractCloudStorageService {

    public DefaultCloudStorageService(CloudStorageConfig config){
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path, String contentType) {
        log.error("云存储服务未配置");
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix, String contentType) {
        log.error("云存储服务未配置");
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path, String contentType) {
        log.error("云存储服务未配置");
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix, String contentType) {
        log.error("云存储服务未配置");
        return null;
    }
}
