package cn.amigosoft.modules.bms.dinning.controller;

import cn.amigosoft.modules.bms.dinning.service.BmsCardLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 一卡通记录表
 */
@RestController
@RequestMapping("bms/cardLog")
@Api(tags = "一卡通记录表 ")
public class BmsCardLogController {

    @Autowired
    private BmsCardLogService bmsCardLogService;

    @PostMapping("import")
    @ApiOperation("上传文件")
    @RequiresPermissions("bms:cardLog:import")
    public void importCsv(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        bmsCardLogService.importCsv(file, response);
    }

}