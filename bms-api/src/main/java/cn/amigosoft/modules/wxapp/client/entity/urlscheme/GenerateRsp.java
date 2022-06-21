package cn.amigosoft.modules.wxapp.client.entity.urlscheme;

import lombok.Data;

@Data
public class GenerateRsp {
    private String openlink; // 生成的小程序scheme码
    private Integer errcode;// 	number	错误码
    private String errmsg;//	string	错误信息
}
