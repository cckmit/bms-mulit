package cn.amigosoft.modules.wxapp.client.entity.auth;

import lombok.Data;

@Data
public class GetAccessTokenReq {
    private String grant_type = "client_credential";// 	string		是	填写 client_credential
    private String appid; // string		是	小程序唯一凭证，即 AppID，可在「微信公众平台 - 设置 - 开发设置」页中获得。（需要已经成为开发者，且帐号没有异常状态）
    private String secret; // string		是	小程序唯一凭证密钥，即 AppSecret，获取方式同 appid
}
