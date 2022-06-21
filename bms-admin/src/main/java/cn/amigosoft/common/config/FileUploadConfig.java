package cn.amigosoft.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * 资源映射路径
 *
 * @author ChenXiong
 */
@Slf4j
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    private static String uploadPath;

    /**
     * 不能直接给变量赋值，需要给set方法赋值
     */
    @Value("${web.UploadFilePath}")
    public void setUploadPath(String uploadPath) {
        FileUploadConfig.uploadPath = uploadPath;
    }

    @PostConstruct
    public void initUploadPath() {
        log.info("-----------------------------------------");
        log.info("上传上件目录：" + uploadPath);
        log.info("如需变更请配置：web.UploadFilePath");
        log.info("-----------------------------------------");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imageUpload/**").addResourceLocations("file:" + uploadPath);
    }
}
