package com.indu.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.indu.zuul.filters.ErrorFilter;
import com.indu.zuul.filters.PostFilter;
import com.indu.zuul.filters.PreFilter;
import com.indu.zuul.filters.RouteFilter;
import com.indu.zuul.filters.custom.AuthHeadFilter;



@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}
	
	
	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
    
    /*@Bean
    AuthHeadFilter authHeadFilter() {
        return new AuthHeadFilter();
    }*/
}
