package core.security.chapter10;

//import jakarta.servlet.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.web.csrf.CsrfToken;
//
//import java.io.IOException;
//
//@Slf4j
//public class CsrfTokenLogger implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Object o = request.getAttribute("_csrf");
//        CsrfToken token = (CsrfToken) o;
//
//        log.info("CSRF token " + token.getToken());
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
