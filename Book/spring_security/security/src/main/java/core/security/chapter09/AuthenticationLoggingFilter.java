package core.security.chapter09;

//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class AuthenticationLoggingFilter extends OncePerRequestFilter {
//
//    private static final Logger log = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String reqId = request.getHeader("Request-Id");
//        log.info("Successfully authenticated request with id " + reqId);
//
//        filterChain.doFilter(request, response);
//    }
////    @Override
////    public void init(FilterConfig filterConfig) throws ServletException {
////        Filter.super.init(filterConfig);
////    }
//
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////        HttpServletRequest req = (HttpServletRequest) request;
////        HttpServletResponse res = (HttpServletResponse) response;
////        String reqId = req.getHeader("Request-Id");
////        log.info("Successfully authenticated request with id " + reqId);
////        System.out.println("AuthenticationLoggingFilter.doFilter" + reqId);
////
////        chain.doFilter(req, res);
////    }
//
////    @Override
////    public void destroy() {
////        Filter.super.destroy();
////    }
//}
