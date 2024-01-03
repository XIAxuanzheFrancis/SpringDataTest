package com.xuanzhe.springbootdata.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource druidDataSource(){
    return new DruidDataSource();
  }
  @Bean
  public ServletRegistrationBean statViewServlet(){
    ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put("loginUsername","admin");
    initParameters.put("loginPassword","123456");

    initParameters.put("allow","");

    initParameters.put("xiaxuanzhe","192.168.11.123");

    bean.setInitParameters(initParameters);
    return bean;
  }
  @Bean
  public FilterRegistrationBean webStatFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    Map<String,String> initParameters = new HashMap<>();
    initParameters.put("exclusion","*.js,*.css,/druid/*");
    bean.setInitParameters(initParameters);
    return bean;
  }
}
