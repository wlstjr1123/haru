package com.douzone.haru.config.auth.scurity;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.haru.config.auth.PrincipalDetails;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return parameter.getParameterAnnotation(AuthUser.class) != null
				&& parameter.getParameterType().equals(PrincipalDetails.class);

	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(parameter)) {
			Object principal = null;
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}

			if (principal == null || principal.getClass() == String.class) {
				return null;
			} else {
				return principal;
			}
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}

	}

}
