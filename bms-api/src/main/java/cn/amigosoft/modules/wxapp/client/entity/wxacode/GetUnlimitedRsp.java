package cn.amigosoft.modules.wxapp.client.entity.wxacode;

import lombok.Data;

@Data
public class GetUnlimitedRsp {

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 数据
     */
    private byte[] buffer;

}
