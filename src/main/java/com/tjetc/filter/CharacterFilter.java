package com.tjetc.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "characterFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "character",value = "UTF-8")
})
public class CharacterFilter implements Filter {
    private String character;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("这是过滤器的初始化方法");
        this.character=filterConfig.getInitParameter("character");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("这是过滤器的过滤方法");
        servletRequest.setCharacterEncoding(this.character);
        servletResponse.setCharacterEncoding(this.character);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("这是过滤器的销毁方法");
    }
}
