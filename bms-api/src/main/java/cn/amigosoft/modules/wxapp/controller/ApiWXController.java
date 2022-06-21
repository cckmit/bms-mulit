package cn.amigosoft.modules.wxapp.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.wxapp.dto.WxappBindDTO;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/wx")
@Api(tags = "小程序")

public class ApiWXController {
    @Autowired
    private BmsWxService wxappService;

    @GetMapping("wxLogin")
    @ApiOperation("微信小程序获取token")
    public Result<Object> wxLogin(String code) {
        return wxappService.wxLogin(code);
    }

    @PostMapping("wxBind")
    @ApiOperation("微信小程序绑定用户信息")
    public Result<Object> wxBind(@RequestBody WxappBindDTO wxappBindDTO) {
        return wxappService.wxBind(wxappBindDTO.getEncryptedData(), wxappBindDTO.getIv(), wxappBindDTO.getCode());
    }

}
