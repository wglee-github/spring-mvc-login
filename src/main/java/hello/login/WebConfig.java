package hello.login;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<Filter> logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<Filter> LoginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
