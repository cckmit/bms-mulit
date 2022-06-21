package cn.amigosoft.modules.wxapp.client.entity.auth;

import lombok.Data;

@Data
public class Code2SessionRsp {
    private String openid;//	string	用户唯一标识
    private String session_key;//	string	会话密钥
    private String unionid;//	string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
    private Integer errcode;//	number	错误码
    private String errmsg;//	string	错误信息
}
