package org.nutz.demo.decoratorby.web.view;

import httl.Context;
import httl.Template;
import httl.web.WebEngine;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.AbstractPathView;

/**
 * 使用 httl 模板生成页面
 * 
 * @Ok("httl:ai.book") 指向 /WEB-INF/ai/book.httl
 * @Ok("httl:/ai/book") 指向/ai/book.httl
 */
public class HttlView extends AbstractPathView implements View {

    protected final String ext = ".httl";

    // private static Engine engine = Engine.getEngine(); // 加载模板引擎

    public HttlView(String dest) {
        super(dest);
    }

    /*
     * 渲染页面
     * 
     * @see org.nutz.mvc.View#render(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public void render(HttpServletRequest req,
                       HttpServletResponse resp,
                       Object obj) throws Throwable {
        // if ("".equals(engine.getTemplatePath())) {
        // String realPath =
        // req.getSession().getServletContext().getRealPath("/");
        // engine.setTemplatePath(realPath);
        // }

        // here move to MainSetup init;
        // if (WebEngine.getServletContext() == null) {
        // synchronized (this) {
        // WebEngine.setServletContext(req.getSession().getServletContext());
        // }
        // }
        String path = evalPath(req, obj);

        path = getTemplatePath(path, req);

        // FileLoader l = new FileLoader();
        // boolean doExists = l.doExists(path, null, realPath);
        // Resource load = l.load(path, null, null);
        // Resource resource = engine.getResource(path);
        Template template = WebEngine.getEngine().getTemplate(path,
                                                              req.getLocale());

        // Context ctx = new Context(); // 生成数据容器对象
        // ctx.set("obj", obj);
        // ctx.set("request", req);
        // ctx.set("response", resp);
        // ctx.set("session", req.getSession());
        //
        //
        // template.merge(ctx, resp.getWriter());
        // Resource resource2 = WebEngine.getEngine().getResource(path);
        Map context = new HashMap();
        context.put("request", req);
        context.put("response", resp);
        Enumeration<String> reqs = req.getAttributeNames();
        while (reqs.hasMoreElements()) {
            String strKey = reqs.nextElement();
            context.put(strKey, req.getAttribute(strKey));
        }

        if (obj instanceof Map) {
            context.putAll((Map) obj);
        } else {
            context.put("obj", obj);
        }
        pubFill(context, req);

        template.render(context, resp);

    }

    protected String getTemplatePath(String path, HttpServletRequest request) {

        // 空路径，采用默认规则
        if (Strings.isBlank(path)) {
            path = Mvcs.getRequestPath(request);
            path = (path.startsWith("/") ? "" : "/")
                   + Files.renameSuffix(path, ext);
        }
        // 绝对路径 : 以 '/' 开头的路径不增加 '/WEB-INF'
        else if (path.charAt(0) == '/') {
            if (!path.toLowerCase().endsWith(ext))
                path += ext;
        }
        // 包名形式的路径
        else {
            //位于 web-inf下面
            path = String.format("/WEB-INF/%s%s", path.replace('.', '/'), ext);
        }

        return path;
    }

    public void pubFill(Map parameters, HttpServletRequest req) {
        parameters.put("contextPath", req.getContextPath());
    }
}