package org.nutz.demo.decoratorby.web.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@InjectName
@IocBean
@At("/jetx")
@Ok("json")
public class UseJetbrickModule {
    private Map<String, Object> make() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("time", Times.sDTms(Times.now()));
        return m;
    }

    @At("/jetx")
    @Ok(".jetx:/WEB-INF/jetx/loginfold/login.jetx")
    // 文件位于 /WEB-INF/jetx/login/login.jetx
    public Map<String, Object> viewByJetx() {
        return make();
    }

    @At("/jsp")
    @Ok("jsp:jsp.time")
    // 文件位于 /WEB-INF/httl/pf/login/login.httl
    public Map<String, Object> viewByJsp() {
        return make();
    }

    @At("/httl")
    @Ok("httl:httl.login")
    // 文件位于 /WEB-INF/jetx/login/login.jetx
    public Map<String, Object> viewByHttl() {
        return make();
    }

}
