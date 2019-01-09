
package com.wondersgroup.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.format.FormatterRegistry;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wondersgroup.framework.interceptor.FromTokenInteractor;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.system.log.config.LogConfig;

@ComponentScan(basePackages = {
        "com.wondersgroup.**.controller", "com.wondersgroup.**.rest", "com.wondersgroup.**.aspect"
}, includeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @Filter(type = FilterType.ANNOTATION, value = RestController.class),
        @Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class),
        @Filter(type = FilterType.ANNOTATION, value = RestControllerAdvice.class),
        @Filter(type = FilterType.ANNOTATION, value = Component.class),
        @Filter(type = FilterType.ANNOTATION, value = LogConfig.class)
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class WebDispatcherServletConfiguration extends WebMvcConfigurationSupport {
	
	@Value("#{mvc['mvc.file.upload.max.size']}")
	public Long fileUploadMaxSize;
	
	@Value("#{mvc['mvc.web.view.prefix']}")
	public String webViewPrefix;
	
	@Value("#{mvc['mvc.web.view.suffix']}")
	public String webViewSuffix;
	
	@Value("#{mvc['mvc.static.resource.path']}")
	public String[] staticResourcesPaths;
	
	@Value("#{mvc['mvc.static.resource.files']}")
	public String[] staticResourcesFiles;
	
	@Bean
	public SessionLocaleResolver localeResolver() {
		
		return new SessionLocaleResolver();
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	@Bean
	public FromTokenInteractor fromTokenInteractor() {
		
		FromTokenInteractor fromTokenInteractor = new FromTokenInteractor();
		return fromTokenInteractor;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(fileUploadMaxSize);
		return multipartResolver;
	}
	
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		addDefaultHttpMessageConverters(converters);
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converter;
				List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
				supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
				jackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
				jackson2HttpMessageConverter.setObjectMapper(objectMapper());
				break;
			}
			if (converter instanceof StringHttpMessageConverter) {
				StringHttpMessageConverter stringHttpMessageConverter = (StringHttpMessageConverter) converter;
				stringHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
				List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
				supportedMediaTypes.add(MediaType.TEXT_PLAIN);
				stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
				break;
			}
		}
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return mapper;
	}
	
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
	}
	
	protected void addCorsMappings(CorsRegistry registry) {
		
	}
	
	protected void addFormatters(FormatterRegistry registry) {
		
	}
	
	protected void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration registration = registry.addInterceptor(localeChangeInterceptor());
		registration.addPathPatterns("/**");
		registration = registry.addInterceptor(fromTokenInteractor());
		registration.addPathPatterns("/**");
	}
	
	protected void addViewControllers(ViewControllerRegistry registry) {
		
	}
	
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		for (String staticResourcesPath : staticResourcesPaths) {
			String[] paths = StringUtils.split(staticResourcesPath, "&&");
			registry.addResourceHandler(paths[0]).addResourceLocations(paths[1]).setCachePeriod(36000);
		}
	}
	
	protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		
	}
	
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		
	}
	
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.defaultContentTypeStrategy(new HeaderContentNegotiationStrategy());
	}
	
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
	
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		
	}
	
	protected void configurePathMatch(PathMatchConfigurer configurer) {
		
		configurer.setUseSuffixPatternMatch(true);
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}
	
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		
		registry.jsp(webViewPrefix, webViewSuffix).cache(true).cacheLimit(3600);
	}
	
	protected void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		
	}
	
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}
}
