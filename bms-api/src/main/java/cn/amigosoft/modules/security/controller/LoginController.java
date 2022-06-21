/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.security.controller;

import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.modules.log.entity.SysLogLoginEntity;
import cn.amigosoft.modules.log.enums.LoginOperationEnum;
import cn.amigosoft.modules.log.enums.LoginStatusEnum;
import cn.amigosoft.modules.log.service.SysLogLoginService;
import cn.amigosoft.modules.security.dto.LoginDTO;
import cn.amigosoft.modules.security.password.PasswordUtils;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.utils.IpUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.asersa.annotation.rsa.RSADecryptMethod;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.modules.security.service.CaptchaService;
import cn.amigosoft.modules.security.service.SysUserTokenService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.enums.UserStatusEnum;
import cn.amigosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * 登录
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@Api(tags = "登录管理")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        //生成图片验证码
        BufferedImage image = captchaService.create(uuid);

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    @RSADecryptMethod
    public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ValidatorUtils.validateEntity(login);

        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
            return new Result().error(ErrorCode.CAPTCHA_ERROR);
        }

        //用户信息
        SysUserDTO user = sysUserService.getByUsername(login.getUsername());

        SysLogLoginEntity loginEntity = new SysLogLoginEntity();
        loginEntity.setOperation(LoginOperationEnum.LOGIN.value());
        loginEntity.setCreateDate(new Date());
        loginEntity.setIp(IpUtils.getIpAddr(request));
        loginEntity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        loginEntity.setIp(IpUtils.getIpAddr(request));

        //用户不存在
        if (user == null) {
            loginEntity.setStatus(LoginStatusEnum.FAIL.value());
            loginEntity.setCreatorName(login.getUsername());
            sysLogLoginService.save(loginEntity);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //密码是用RSA公钥加密了的，这里需要用私钥解出来
        if (StringUtils.isEmpty(login.getPassword())) {
            log.error("解密失败或者服务端秘钥已过期，请重新获取");
            return new Result().error(ErrorCode.RSA_ERROR, "解密失败或者服务端秘钥已过期，请重新获取");
        }

        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            loginEntity.setStatus(LoginStatusEnum.FAIL.value());
            loginEntity.setCreator(user.getId());
            loginEntity.setCreatorName(user.getUsername());
            sysLogLoginService.save(loginEntity);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            loginEntity.setStatus(LoginStatusEnum.LOCK.value());
            loginEntity.setCreator(user.getId());
            loginEntity.setCreatorName(user.getUsername());
            sysLogLoginService.save(loginEntity);

            throw new RenException(ErrorCode.ACCOUNT_DISABLE);
        }

        //登录成功
        loginEntity.setStatus(LoginStatusEnum.SUCCESS.value());
        loginEntity.setCreator(user.getId());
        loginEntity.setCreatorName(user.getUsername());
        sysLogLoginService.save(loginEntity);

        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @ApiOperation(value = "退出")
    public Result logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity loginEntity = new SysLogLoginEntity();
        loginEntity.setOperation(LoginOperationEnum.LOGOUT.value());
        loginEntity.setIp(IpUtils.getIpAddr(request));
        loginEntity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        loginEntity.setIp(IpUtils.getIpAddr(request));
        loginEntity.setStatus(LoginStatusEnum.SUCCESS.value());
        loginEntity.setCreator(user.getId());
        loginEntity.setCreatorName(user.getUsername());
        loginEntity.setCreateDate(new Date());
        sysLogLoginService.save(loginEntity);

        return new Result();
    }

}