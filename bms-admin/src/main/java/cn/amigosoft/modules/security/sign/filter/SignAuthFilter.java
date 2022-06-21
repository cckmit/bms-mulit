package cn.amigosoft.modules.security.sign.filter;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.utils.JsonUtil;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.common.utils.asersa.EncryUtil;
import cn.amigosoft.common.utils.asersa.HttpUtils;
import cn.amigosoft.common.utils.asersa.RSAConstant;
import cn.amigosoft.common.utils.asersa.SignUtil;
import cn.amigosoft.modules.security.config.SignConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;

/**
 * 签名过滤器
 *
 * @author xuefh
 * @date 10:03 2020/2/9
 * @Component 注册 Filter 组件
 */
@Slf4j
public class SignAuthFilter implements Filter {

    private SignConfig config;
    private EncryUtil encryUtil;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext Context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        config = (SignConfig) Context.getBean("signConfig");
        encryUtil = (EncryUtil) Context.getBean("encryUtil");
        log.info("初始化 SignAuthFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        //上传、导出可用axios调用
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        // 依赖shiro 中的匹配规则进行筛选
        PatternMatcher matcher = new AntPathMatcher();
        boolean signIngore = false;
        String uri = requestWrapper.getRequestURI();
        String contextPath = requestWrapper.getContextPath();
        String method = requestWrapper.getMethod();
        // 获取header数据
        String sign = requestWrapper.getHeader(RSAConstant.SIGN_HEADER);
        String token = requestWrapper.getHeader(Constant.TOKEN_HEADER);

        if (uri.contains(contextPath)) {
            uri = uri.replaceFirst(contextPath, "/");
        }

        if (uri.contains("//")) {
            uri = uri.replaceFirst("//", "/");
        }
        log.info(" uri==>{},method==>{}", new Object[]{uri, method});
        // 过滤不需要签名的链接
        for (String url : config.getUriIgnoreList()) {
            if (matcher.matches(url, uri)) {
                signIngore = true;
                break;
            }
        }
        //签名没有打开或者是忽略的签名的url
        if (!config.isOpen() || signIngore || "OPTIONS".equals(method)) {
            chain.doFilter(requestWrapper, response);
        } else {
            // 获取全部参数(包括URL和body上的)
            SortedMap<String, String> allParams = HttpUtils.getAllParams(requestWrapper);
            boolean isSigned = false;
            log.info("header  sign==>{},token==>{}", new Object[]{sign, token});
            if (StringUtil.isNotBlank(token)) {
                sign = encryUtil.AESDecryptByToken(sign, token);
                isSigned = SignUtil.verifySign(allParams, sign);
            } else {
                // 对参数进行签名验证
                isSigned = SignUtil.verifySign(allParams, sign);
            }
            if (isSigned) {
                chain.doFilter(requestWrapper, response);
            } else {
                // 校验失败返回前端
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.append(JsonUtil.entityToString(new Result().error(404, "签名校验失败"), true));
            }
        }

    }

    @Override
    public void destroy() {

        log.info("销毁 SignAuthFilter");
    }
}
