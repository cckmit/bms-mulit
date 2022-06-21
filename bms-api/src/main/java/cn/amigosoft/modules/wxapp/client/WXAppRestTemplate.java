package cn.amigosoft.modules.wxapp.client;

import cn.amigosoft.modules.wxapp.client.entity.auth.Code2SessionReq;
import cn.amigosoft.modules.wxapp.client.entity.auth.Code2SessionRsp;
import cn.amigosoft.modules.wxapp.client.entity.auth.GetAccessTokenReq;
import cn.amigosoft.modules.wxapp.client.entity.auth.GetAccessTokenRsp;
import cn.amigosoft.modules.wxapp.client.entity.urlscheme.GenerateReq;
import cn.amigosoft.modules.wxapp.client.entity.urlscheme.GenerateRsp;
import cn.amigosoft.modules.wxapp.client.entity.wxacode.GetUnlimitedReq;
import cn.amigosoft.modules.wxapp.client.entity.wxacode.GetUnlimitedRsp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @ClassName : WXAppRestTemplate
 * @Description : 微信小程序请求客户端
 */
public class WXAppRestTemplate extends RestTemplate {
    private String accessToken;
    private ObjectMapper objectMapper = new ObjectMapper();

    public WXAppRestTemplate() {
    }

    public WXAppRestTemplate(String accessToken) {
        this.accessToken = accessToken;
    }

    public HttpEntity createJsonHttpEntity(Object obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestData = null;
        try {
            requestData = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<>(requestData, headers);
        return request;
    }

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）。调用绝大多数后台接口时都需使用 access_token，开发者需要进行妥善保存。
     */
    private static String auth_getAccessToken_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s";

    public GetAccessTokenRsp auth_getAccessToken(GetAccessTokenReq getAccessTokenReq) {
        String url = String.format(auth_getAccessToken_url, getAccessTokenReq.getGrant_type(), getAccessTokenReq.getAppid(), getAccessTokenReq.getSecret());
        GetAccessTokenRsp getAccessTokenRsp = this.getForObject(url, GetAccessTokenRsp.class);
        return getAccessTokenRsp;
    }

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     */

    private static String auth_code2Session_url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s";

    public Code2SessionRsp auth_code2Session(Code2SessionReq code2SessionReq) {
        String url = String.format(auth_code2Session_url, code2SessionReq.getAppid(), code2SessionReq.getSecret(), code2SessionReq.getJs_code(), code2SessionReq.getGrant_type());
        // Content-Type 为 text/plain 识别不出来，手动自己解析
        ResponseEntity<byte[]> responseEntity = this.exchange(url, HttpMethod.POST, createJsonHttpEntity(code2SessionReq), byte[].class);
        byte[] body = responseEntity.getBody();
        Code2SessionRsp code2SessionRsp = new Code2SessionRsp();
        try {
            code2SessionRsp = objectMapper.readValue(body, Code2SessionRsp.class);
        } catch (IOException e) {
            e.printStackTrace();
            code2SessionRsp.setErrcode(-1);
            code2SessionRsp.setErrmsg(e.getMessage());
        }
        return code2SessionRsp;
    }


    /**
     * 获取小程序scheme码，适用于短信、邮件、外部网页等拉起小程序的业务场景。通过该接口，可以选择生成到期失效和永久有效的小程序码，目前仅针对国内非个人主体的小程序开放，
     */
    private static String urlscheme_generate_url = "https://api.weixin.qq.com/wxa/generatescheme?access_token=%s";

    public GenerateRsp urlscheme_generate(GenerateReq generateReq) {
        String url = String.format(urlscheme_generate_url, this.accessToken);
        GenerateRsp generateRsp = this.postForObject(url, createJsonHttpEntity(generateReq), GenerateRsp.class);
        return generateRsp;
    }

    /**
     * 获取小程序码，适用于需要的码数量极多的业务场景。通过该接口生成的小程序码，永久有效，数量暂无限制。
     */
    private static String wxacode_getUnlimited_url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";

    public GetUnlimitedRsp wxacode_getUnlimited(GetUnlimitedReq getUnlimitedReq) {
        String url = String.format(wxacode_getUnlimited_url, this.accessToken);
        ResponseEntity<byte[]> responseEntity = this.exchange(url, HttpMethod.POST, createJsonHttpEntity(getUnlimitedReq), byte[].class);
        HttpHeaders headers = responseEntity.getHeaders();
        byte[] body = responseEntity.getBody();
        GetUnlimitedRsp getUnlimitedRsp = new GetUnlimitedRsp();
        if ("image/jpeg".equals(headers.getContentType().toString().trim())) {
            System.out.println(headers.getContentType());
            getUnlimitedRsp.setErrcode(0);
            getUnlimitedRsp.setBuffer(body);
        } else {
            try {
                getUnlimitedRsp = objectMapper.readValue(body, GetUnlimitedRsp.class);
            } catch (IOException e) {
                e.printStackTrace();
                getUnlimitedRsp.setErrcode(-1);
                getUnlimitedRsp.setErrmsg(e.getMessage());
            }
        }
        return getUnlimitedRsp;
    }


}
