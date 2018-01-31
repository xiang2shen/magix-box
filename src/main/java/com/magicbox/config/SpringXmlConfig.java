package com.magicbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 使在spring boot中也能使用xml配置
 * 
 * @author xiangshuo
 *
 */
@Configuration
@ImportResource(locations={"classpath:spring-context.xml"})
public class SpringXmlConfig {

}
