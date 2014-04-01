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

/**
 * 使用 httl 模板生成页面,httl位于jar中
 * 
 */
public class HttlJarView extends HttlView {

	// private static Engine engine = Engine.getEngine(); // 加载模板引擎

	public HttlJarView(String dest) {
		super(dest);
	}

	/*
	 * 渲染页面
	 * 
	 * @see org.nutz.mvc.View#render(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj)
			throws Throwable {

		String path = evalPath(req, obj);

		// 空路径，采用默认规则
		if (Strings.isBlank(path)) {
			path = Mvcs.getRequestPath(req);
			path = (path.startsWith("/") ? "" : "/") + Files.renameSuffix(path, ext);
		}
		// 绝对路径 : 以 '/' 开头的路径不增加 '/WEB-INF'
		else if (path.charAt(0) == '/') {
			if (!path.toLowerCase().endsWith(ext))
				path += ext;
		}
		// 包名形式的路径
		else {
			path = "/" + path.replace('.', '/') + ext;
		}

		Template template = WebEngine.getEngine().getTemplate(path);

		Map context = new HashMap();
		context.put("request", req);
		context.put("response", resp);
		Enumeration<?> reqs = req.getAttributeNames();
		while (reqs.hasMoreElements()) {
			String strKey = (String) reqs.nextElement();
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
}