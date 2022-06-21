package cn.amigosoft.common.utils.asersa.annotation.rsa;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 *
 * 接口RSA加密注解
 * 安全字段注解
 * 加在需要加密/解密的方法上
 * 实现自动加密解密
 *
 * @author: xuefenghai
 * @date:2018/12/27
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RSAEncryptMethod {
}
