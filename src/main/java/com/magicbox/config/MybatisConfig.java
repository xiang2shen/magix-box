package com.magicbox.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MybatisConfig implements EnvironmentAware {
	
	private String basePackage;

	@Override
	public void setEnvironment(Environment env) {
        basePackage = new RelaxedPropertyResolver(env, "mybatis.").getProperty("basePackage");	// 由于mybatis过早扫描mapper，故无法通过@Value直接获取配置
    }
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer scan = new MapperScannerConfigurer();
		scan.setBasePackage(basePackage);
		return scan;
	}
	
}
