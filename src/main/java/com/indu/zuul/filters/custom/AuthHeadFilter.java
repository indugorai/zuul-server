package com.indu.zuul.filters.custom;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;



/**
 * Typically in microservices, we will use OAuth service for authentication and authorization. 
 * Once the client is authenticated OAuth service will generate a token which should be included in 
 * the requests making to other microservices so that client need not be authenticated for every service separately. 
 * We can use Zuul filter to implement features like this.
 * 
 * @author indgorai
 *
 */
public class AuthHeadFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
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
        if (request.getAttribute("AUTH_HEADER") == null) {
            //generate or get AUTH_TOKEN, ex from Spring Session repository
            String sessionId = UUID.randomUUID().toString();
            ctx.addZuulRequestHeader("AUTH_HEADER", sessionId);
        }
        return null;
    }

}