package org.hifumi.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 使用@AutoConfiguration，让SpringBoot自动注入bean
 * 需要注入的bean写在@Import的类里面
 * 把org.hifumi.config.CommonAutoConfig写入resources目录下的META-INF/spring/
 * 文件名固定是org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 在SpringBoot2.7之前则是spring.factories
 *
 * 也可以不要这个类，直接在CommonConfig类里使用@AutoConfiguration
 * AutoConfiguration注解里整合了@Configuration，但proxyBeanMethods = false，默认true
 * proxyBeanMethods = true表示强制@Bean注入的对象为单例，否则每次调用都会新建对象
 */
@AutoConfiguration
@Import(CommonConfig.class)
public class CommonAutoConfig {

}
