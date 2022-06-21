package cn.amigosoft.modules.wxapp.client.entity.auth;

import lombok.Data;

@Data
public class Code2SessionReq {

    private String appid;//	string		是	小程序 appId
    private String secret;//	string		是	小程序 appSecret
    private String js_code;//	string		是	登录时获取的 code
    private String grant_type = "authorization_code";//	string		是	授权类型，此处只需填写 authorization_code

}
