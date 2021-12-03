package com.plut.demo.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by jyb on 18-4-7.
// */
//public class AccessFilter extends ZuulFilter {
//
//    /**
//     * 过滤器类型 决定过滤器请求在哪个生命周期中执行
//     * @return
//     */
//    @Override
//    public String filterType() {
//        return "pre";//在请求被路由之前执行
//    }
//
//    /**
//     * 过滤器执行顺序
//     * @return
//     */
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    /**
//     * 是否需要执行
//     * @return
//     */
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    /**
//     * 过滤逻辑
//     * @return
//     */
//    @Override
//    public Object run() {
//        RequestContext requestContext =RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null || "".equals(accessToken)){
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(401);
//            requestContext.setResponseBody("{\"result\":\"accessToken is null.\"}");
//            return null;
//        }
//        return null;
//    }
//}
