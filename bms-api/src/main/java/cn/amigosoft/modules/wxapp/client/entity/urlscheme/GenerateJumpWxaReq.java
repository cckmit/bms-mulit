package cn.amigosoft.modules.wxapp.client.entity.urlscheme;

import lombok.Data;

@Data
public class GenerateJumpWxaReq {
    private String path;//	string		是	通过scheme码进入的小程序页面路径，必须是已经发布的小程序存在的页面，不可携带query。path为空时会跳转小程序主页。
    private String query;//	string		是	通过scheme码进入小程序时的query，最大1024个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~

}
