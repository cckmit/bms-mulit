package cn.amigosoft.modules.oss.controller;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import cn.amigosoft.modules.oss.entity.SysOssEntity;
import cn.amigosoft.modules.oss.service.SysOssService;
import cn.amigosoft.modules.wxapp.annotation.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 */
@RestController
@RequestMapping("sys/oss")
@Api(tags="文件上传")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;

    private final static String KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    @Login
    @PostMapping("upload")
    @ApiOperation(value = "上传文件")
    public Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        //上传文件
        String contentType = file.getContentType();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), extension, contentType);
        //保存文件信息
        //return new Result<>();
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.insert(ossEntity);

        Map<String, Object> data = new HashMap<>(1);
        data.put("src", url);

        return new Result<Map<String, Object>>().ok(data);
    }


    @PostMapping("wx-upload")
    @ApiOperation(value = "上传文件")
    public Result<Map<String, Object>> wxUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return this.upload(file);
    }
}
