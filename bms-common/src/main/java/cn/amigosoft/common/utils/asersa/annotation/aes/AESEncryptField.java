package cn.amigosoft.common.utils.asersa.annotation.aes;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 安全字段注解
 * 加在需要加密/解密的字段上
 *
 * @author: xuefenghai
 * @date:2018/12/27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface AESEncryptField {
}
