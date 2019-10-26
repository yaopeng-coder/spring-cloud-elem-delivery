package cn.hust.apigateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-25 11:02
 *
 * c -- cross o--origin  r -- resource  s -- sharing
 **/

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config= new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setMaxAge(300l);

        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

}
