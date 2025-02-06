package br.com.login.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.login.backend.filter.TokenFilter;

@Configuration
public class JWTConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> filtroDeRotas() {
        FilterRegistrationBean<TokenFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new TokenFilter());
        filterRegistrationBean.addUrlPatterns("/usuarios/*");
        return filterRegistrationBean;
    }
}
