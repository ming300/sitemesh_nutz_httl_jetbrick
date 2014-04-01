package org.nutz.demo.decoratorby.web.view;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;

public class HttlViewMaker implements ViewMaker {
	public View make(Ioc ioc, String type, String value) {
		if ("httl".equalsIgnoreCase(type)) {
			return new HttlView(value);
		} else if ("Httljar".equalsIgnoreCase(type)) {
			return new HttlJarView(value);
		}
		return null;
	}
}
