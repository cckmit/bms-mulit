package cn.amigosoft.common.utils.asersa;

import cn.amigosoft.common.utils.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.SortedMap;

/**
 * 签名工具类
 *
 * @author xuefh
 * @date 10:03 2020/2/9
 */
@Slf4j
public class SignUtil {
    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, String> params,String sign) {
        if (StringUtils.isEmpty(sign)) {
            log.info("解密后 sign ==>{}, 被加密 params==>{},签名校验==>{}",
                   new Object[]{sign, JsonUtil.entityToString(params),false} );
            return false;
        }
        // 把参数加密
        String paramsSign = getParamsSign(params);
        boolean isPass = !StringUtils.isEmpty(paramsSign) && sign.equals(paramsSign);
        log.info("解密后 sign ==>{}, 被加密 params==>{},重新生成 Sign==>{},签名校验==>{}", new Object[]{
                sign, JsonUtil.entityToString(params),paramsSign,isPass});
        return isPass;
    }

    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, String> params) {
        String paramsJsonStr = JSONObject.toJSONString(params);
        return DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();
    }
}