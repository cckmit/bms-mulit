package cn.amigosoft.common.utils.asersa.aspect.aes;

import cn.amigosoft.common.utils.HttpContextUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.asersa.EncryUtil;
import cn.amigosoft.common.utils.asersa.RSAConstant;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.utils.asersa.annotation.aes.AESEncryptField;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 安全字段加密解密切面
 *
 * @author: xuefenghai
 * @date:2019/5/30
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class AESEncryptAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EncryUtil encryUtil;

    @Pointcut("@annotation(cn.amigosoft.common.utils.asersa.annotation.aes.AESEncryptMethod)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            //判断注解鉴权方式
            Object[] arr = joinPoint.getArgs();
            //请求相关信息
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            //从header中获取rsa_uuid
            String rsa_uuid = request.getHeader(RSAConstant.RSA_UUID_HEADER);
            for (Object obj : arr) {
                handleEncrypt(obj, rsa_uuid);
            }
            responseObj = joinPoint.proceed();
        } catch (RenException e) {
            Result result = new Result();
            result.error(e.getCode(), e.getMsg());
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.error("EncryptFieldAspect处理出现异常{}", e);
            return new Result().error("DecryptAspect 处理出现异常");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("EncryptFieldAspect处理出现异常{}", throwable);
            return new Result().error("DecryptAspect 处理出现异常");
        }
        return responseObj;
    }

    /**
     * 处理加密
     *
     * @param responseObj
     */
    private void handleEncrypt(Object responseObj, String rsa_uuid) throws Exception {
        if (Objects.isNull(responseObj)) {
            return;
        }
        Field[] fields = responseObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AESEncryptField.class)) {
                field.setAccessible(true);
                String encryptValue = (String) field.get(responseObj);
                String plaintextValue = this.encryUtil.AESDecryptByToken(encryptValue, rsa_uuid);
                field.set(responseObj, plaintextValue);
            }

        }

    }
}
