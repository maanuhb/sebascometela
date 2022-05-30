package com.uca.spring;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@ComponentScan
public class DemoApplication extends SpringBootServletInitializer {
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
     return application.sources(DemoApplication.class);
  }
  
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
  
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
     registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
  }

  
}
