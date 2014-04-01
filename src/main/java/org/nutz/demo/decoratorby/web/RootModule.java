/**
 * 
 * @author mawenming at 2009-12-14 下午03:32:29
 */
package org.nutz.demo.decoratorby.web;

import jetbrick.template.web.nutz.JetTemplateViewMaker;

import org.nutz.demo.decoratorby.web.module.UseJetbrickModule;
import org.nutz.demo.decoratorby.web.view.HttlViewMaker;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.AnnotationIocProvider;

/**
 * 
 * <p>
 * 版权所有：长春易申软件有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>
 * 侵权者将受到法律追究。
 * <p>
 * DERIVED FROM: NONE
 * <p>
 * PURPOSE:
 * <p>
 * DESCRIPTION:
 * <p>
 * UPDATE: mawenming(马文明)
 * <p>
 * DATE: 2009-12-14 下午03:32:29
 * <p>
 * HISTORY: 1.0
 * 
 * @version 1.0
 * @author mawenming(马文明)
 * @since java jdk1.6.0_11<br>
 * @beanid <br>
 * 
 *         功能描述:<br>
 */

/**
 * 本类为整个应用的默认模块类。在这个类上，你可以：
 * <ul>
 * <li>通过 '@Modules' 注解声明整个应用有哪些模块
 * <li>通过 '@IocBy' 注解声明整个应用，应采用何种方式进行反转注入。如果没有声明，整个应用将不支持 Ioc
 * <li>通过 '@Localization' 注解声明整个应用的本地地化字符串的目录
 * <li>通过 '@SetupBy' 注解声明应用启动的关闭时，应该进行的处理。（通过 Setup 接口）
 * <li>通过 '@Ok' 注解声明整个应用默认的成功视图
 * <li>通过 '@Fail' 注解声明整个应用默认的失败视图
 * </ul>
 * 
 * 该主入口类无法继承,仅仅能够把相关语句copy到子系统中进行加载, 同时需要在web.xml中进行替换
 * 
 * 
 * 
 */
@Modules(value = {UseJetbrickModule.class}, scanPackage = true)
// @SetupBy(YSetup.class)
@Localization("msg")
@Views({HttlViewMaker.class, JetTemplateViewMaker.class})
@Fail("json")
@Ok("json")
// @IocBy(type = SpringIocProvider.class, args = {})
@IocBy(type = AnnotationIocProvider.class,
        args = {"org.nutz.demo.decoratorby",})
@Encoding(input = "utf8", output = "utf8")
public class RootModule {

}
