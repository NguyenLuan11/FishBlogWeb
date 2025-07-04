package com.tnluan.fishblogweb.config;

import com.tnluan.fishblogweb.interceptor.AdminInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * WebConfig is a central configuration class that implements WebMvcConfigurer.
 * It is responsible for:
 * - Registering interceptors
 * - Exposing static resources
 * - Enabling support for HTTP methods like PUT/DELETE via HiddenHttpMethodFilter
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;

    /**
     * Registers a HiddenHttpMethodFilter to allow using PUT, DELETE, etc.
     * from HTML forms via a hidden input field (_method).
     *
     * @return the configured FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * Maps URL patterns like /upload/** to serve static files from the "upload" directory on the server.
     * This is typically used for serving uploaded images or documents.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get("upload").toAbsolutePath();
        String uploadDir = "file:" + uploadPath.toString() + "/";

        registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadDir);
    }

    /**
     * Registers the AdminInterceptor to protect all routes under /admin/**
     * except for explicitly excluded paths such as login and static assets.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/doLogin",
                        "/admin/css/**",
                        "/admin/js/**"
                );
    }
}
