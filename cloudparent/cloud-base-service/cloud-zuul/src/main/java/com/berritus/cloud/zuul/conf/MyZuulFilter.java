package com.berritus.cloud.zuul.conf;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //类型有pre，post，routing， error
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤顺序，值越小，越早执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if(StringUtils.isEmpty(accessToken)){
            System.out.println("access_token is null");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("access_token is null");
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
}
