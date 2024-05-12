package com.liddhome.config;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.alibaba.druid.pool.DruidDataSource;
import com.liddhome.admin.filter.AdminLoginFilter;
import com.liddhome.filter.LoginFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.liddhome.service", "com.liddhome.admin.service", "com.liddhome.web", "com.liddhome.admin.web"})
@MapperScan(basePackages = {"com.liddhome.dao", "com.liddhome.admin.dao"})
public class ApplicationConfig implements WebMvcConfigurer {
    @Value("${dbcp.driverClassName}")
    private String driverClassName;

    @Value("${dbcp.url}")
    private String url;

    @Value("${dbcp.username}")
    private String username;

    @Value("${dbcp.password}")
    private String password;

    @Value("${dbcp.maxActive}")
    private int maxActive;

    @Value("${dbcp.maxWait}")
    private int maxWait;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1048576); // 设置最大上传文件大小
        resolver.setDefaultEncoding("utf-8"); // 设置默认编码
        return resolver;
    }

    @Bean
    //JdbcTemplate
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/cartItem/*", "/jsps/cart/*", "/jsps/user/pwd.jsp", "/order/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminLoginFilter> adminLoginFilterRegistrationBean() {
        FilterRegistrationBean<AdminLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminLoginFilter());
        registrationBean.addUrlPatterns("/adminjsps/admin/*", "/admin/*");
        return registrationBean;
    }
}