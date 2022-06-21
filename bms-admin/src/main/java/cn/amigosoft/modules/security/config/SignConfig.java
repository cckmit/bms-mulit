package cn.amigosoft.modules.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 加解密配置类
 */
@ConfigurationProperties(prefix = "spring.sign")
@Configuration
@Slf4j
public class SignConfig {

    @PostConstruct
    public void init() {
        List<String> defaulUriIgnoreList = new ArrayList<>();
        defaulUriIgnoreList.add("/sys/dict/listByDictType/**");
        defaulUriIgnoreList.add("/sys/dict/list");
        defaulUriIgnoreList.add("/bigScreen/**");
        defaulUriIgnoreList.add("/cmdb/content/getTypeList");
        defaulUriIgnoreList.add("/webjars/**");
        defaulUriIgnoreList.add("/druid/**");
        defaulUriIgnoreList.add("/login");
        defaulUriIgnoreList.add("/swagger/**");
        defaulUriIgnoreList.add("/v2/api-docs");
        defaulUriIgnoreList.add("/swagger-ui.html");
        defaulUriIgnoreList.add("/swagger-resources/**");
        defaulUriIgnoreList.add("/service/**");
        defaulUriIgnoreList.add("/editor-app/**");
        defaulUriIgnoreList.add("/diagram-viewer/**");
        defaulUriIgnoreList.add("/modeler.html");
        defaulUriIgnoreList.add("/captcha");
        defaulUriIgnoreList.add("/favicon.ico");
        defaulUriIgnoreList.add("/ecs/**");
        defaulUriIgnoreList.add("/common/rsa/publicKey");
        defaulUriIgnoreList.add("/common/rsa/transferKey");
        defaulUriIgnoreList.add("/logout");
        defaulUriIgnoreList.add("/sys/menu/nav");
        defaulUriIgnoreList.add("/sys/menu/permissions");
        defaulUriIgnoreList.add("/sys/user/info");
        uriIgnoreList.addAll(defaulUriIgnoreList);
        log.info("+++++++++++++++++++SignConfig+++++++SignConfig=>{} ", this.toString());
    }


    /**
     * 忽略对请求内容进行解密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    private List<String> uriIgnoreList = new ArrayList<>();

    /**
     * 开启签名校验
     */
    private boolean open = true;


    @Override
    public String toString() {
        return "SignConfig{" +
                "uriIgnoreList=" + uriIgnoreList +
                ", open=" + open +
                '}';
    }

    public List<String> getUriIgnoreList() {
        return uriIgnoreList;
    }

    public void setUriIgnoreList(List<String> uriIgnoreList) {
        this.uriIgnoreList = uriIgnoreList;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
