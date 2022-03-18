package com.example.demo.common;



import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {

@Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){return new
        PaginationInnerInterceptor();}
}
