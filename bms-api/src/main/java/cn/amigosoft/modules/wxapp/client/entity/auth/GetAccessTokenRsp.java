package cn.amigosoft.modules.wxapp.client.entity.auth;

import lombok.Data;

@Data
public class GetAccessTokenRsp {
    private String access_token;//	string	获取到的凭证
    private Integer expires_in;//	number	凭证有效时间，单位：秒。目前是7200秒之内的值。
    private Integer errcode;//	number	错误码
    private String errmsg;//	string	错误信息
}
