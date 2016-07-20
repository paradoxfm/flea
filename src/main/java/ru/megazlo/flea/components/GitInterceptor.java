package ru.megazlo.flea.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GitInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private GitRepositoryState gitState;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		request.setAttribute("git", gitState);
		super.postHandle(request, response, handler, modelAndView);
	}
}
