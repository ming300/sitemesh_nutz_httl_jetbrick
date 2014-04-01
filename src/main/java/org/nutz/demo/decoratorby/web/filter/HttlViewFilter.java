package org.nutz.demo.decoratorby.web.filter;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.nutz.demo.decoratorby.web.view.HttlView;
import org.nutz.lang.Lang;

/**
 * @author mawm(ming300@gmail.com) <br/>
 *         这个filter的目的是用于sitemesh的decorator能够使用.jetx文件进行处理, 也能够正常实现decorator,但是,
 * 
 *         在nutz中,增加jetx的view类型,不能看正常的被sitemesh进行渲染,只能够享受jetx自身的内容,所以这里
 *         做一个记录,暂时通过httl的方式再次实现一个试试. <br/>
 *         <code>
 * <sitemesh>
 * <mapping path="*" decorator="/sitemesh_decorator/default/decorator.jetx"/>
 * </sitemesh>
 * </code>
 * 
 * 
 *         <code>
 * at web.xml
 * 
 *  <filter>
        <filter-name>jetbrickViewFilter</filter-name>
        <filter-class>com.ccesun.common.base.web.JetbrickViewFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>jetbrickViewFilter</filter-name>
        <url-pattern>*.jetx</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
    </filter-mapping>
    
 * </code>
 */
public class HttlViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            // String print = RequestUtil.print(req, null);
            // Logs.get().debug(print);
            String name = req.getRequestURI();
            // 因为 URI中包含contextPath,所以取消掉

            String contextPath = req.getContextPath();
            String path = StringUtils.removeStart(name, contextPath);
            Map context = new HashMap();
            context.put("name", "mawm");
            context.put("___isDevMode", true);
            context.put("base", contextPath);

            HttlView view = new HttlView(path);

            view.render(req, res, context);
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
        catch (Throwable e) {
            throw Lang.wrapThrow(e);
        }
    }

    @Override
    public void destroy() {

    }

}
