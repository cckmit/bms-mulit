package cn.amigosoft.common.utils.asersa.aspect.aes;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.asersa.EncryUtil;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.utils.asersa.annotation.aes.AESEncryptField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
@Slf4j
public class AESDecryptAspect {

    @Autowired
    private EncryUtil encryUtil;

    @Pointcut("@annotation(cn.amigosoft.common.utils.asersa.annotation.aes.AESDecryptMethod)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        //获取token绑定的aeskey
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //从header中获取token
        String token = request.getHeader(Constant.TOKEN_HEADER);
        //校验
        if (StringUtils.isBlank(token)) {
            return new Result().error("token 不存在");
        }
        try {
            //判断注解鉴权方式
            Object[] arr = joinPoint.getArgs();
            for (Object obj : arr) {
                handleDecrypt(obj, token);
            }
            responseObj = joinPoint.proceed();
        } catch (RenException e) {
            Result result = new Result();
            result.error(e.getCode(), e.getMsg());
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.error("DecryptAspect 处理出现异常{}", e);
            return new Result().error("DecryptAspect 处理出现异常");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("DecryptAspect 处理出现异常{}", throwable);
            return new Result().error("DecryptAspect 处理出现异常");

        }
        return responseObj;
    }

    /**
     * 处理解密
     *
     * @param responseObj
     */
    private Object handleDecrypt(Object responseObj, String token) throws Exception {

        if (Objects.isNull(responseObj)) {
            return null;
        }
        Field[] fields = responseObj.getClass().getDeclaredFields();

        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(AESEncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String encryptValue = (String) field.get(responseObj);
                String plaintextValue = this.encryUtil.AESDecryptByToken(encryptValue, token);
                field.set(responseObj, plaintextValue);
            }
        }

        return responseObj;
    }
}
