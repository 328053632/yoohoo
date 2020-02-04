package com.yoohoo.en.web.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;
import com.alibaba.fastjson.JSON;

public class JsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> data, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		resp.getWriter().write(JSON.toJSONString(data));;  
	}

}
