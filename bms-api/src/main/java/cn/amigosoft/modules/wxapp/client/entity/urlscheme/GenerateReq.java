package cn.amigosoft.modules.wxapp.client.entity.urlscheme;

import lombok.Data;

@Data
public class GenerateReq {
    private GenerateJumpWxaReq jump_wxa;    //Object		否	跳转到的目标小程序信息。
    private Boolean is_expire = false;    //boolean	false	否	生成的scheme码类型，到期失效：true，永久有效：false。
    private Integer expire_time;    //number		否	到期失效的scheme码的失效时间，为Unix时间戳。生成的到期失效scheme码在该时间前有效。最长有效期为1年。生成到期失效的scheme时必填。
}
