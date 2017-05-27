package com.notoeat.core.service;

import javax.servlet.*;
import java.io.IOException;

/**
 * 采用Filter统一处理字符集.
 * @author Summer
 *
 */
public class CharsetEncodingFilter implements Filter {

    //定义成员变量.
    private String encoding;
    //销毁.
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /**
     * 过滤器真正执行的处理功能.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

}