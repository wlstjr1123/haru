package com.douzone.haru.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.haru.config.auth.scurity.AuthUserHandlerMethodArgumentResolver;

@Configuration
@PropertySource({"classpath:com/douzone/haru/config/WebConfig.properties"})
public class WebConfig implements WebMvcConfigurer {
	// 아규먼트 리졸버
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}	
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(handlerMethodArgumentResolver());
	}
	
	//FileUpload
	@Autowired
	private Environment env;
	
		// Message Converters
		@Bean
		public StringHttpMessageConverter stringHttpMessageConverter() {
			StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();  
			messageConverter.setSupportedMediaTypes(
				Arrays.asList(
					new MediaType("text", "html", Charset.forName("utf-8"))
				)
			);
			return messageConverter;
		}
		
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			converters.add(stringHttpMessageConverter());
		}
		
		//FileUpload (ResourceMapping (URL Magic Mapping))
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			// TODO Auto-generated method stub
			registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
			.addResourceLocations("file:"+env.getProperty("fileupload.uploadLocation"));
			
			registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping2"))
			.addResourceLocations("file:"+env.getProperty("fileupload.uploadLocation2"));	
			
		}
		
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("GET", "POST","PUT", "DELETE");
		}
}
