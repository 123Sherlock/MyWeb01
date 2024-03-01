package org.hifumi;

import org.hifumi.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * SpringBootApplication注解整合了@ComponentScan自动扫描bean
 * ComponentScan无参数时，默认扫描当前类所在的包及其子包下的所有bean
 * 手动指定bean扫描的范围，@ComponentScan(basePackages = "org.hifumi")
 *
 * SpringBootApplication注解整合了@EnableAutoConfiguration
 * EnableAutoConfiguration注解又整合了@Import(AutoConfigurationImportSelector.class)
 * AutoConfigurationImportSelector类实现了ImportSelector接口
 * 追踪源码会发现META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports配置了许多预先注入的bean
 * 这个AutoConfiguration.imports在spring-boot-autoconfigure包里
 * spring-boot-autoconfigure包又被spring-boot-starter包引用
 * 在SpringBoot2.7之前，不是配置在AutoConfiguration.imports而是相同目录下的spring.factories
 * 在SpringBoot2.7-3.0，同时兼容AutoConfiguration.imports和spring.factories
 * 在SpringBoot3.0以后，仅支持spring.factories
 *
 * 可通过@Import注入第三方bean，里面的参数支持数组，即@Import({MyClass1.class, MyClass2.class})
 * 也可在@Import里填入ImportSelector接口的实现类，在该类里编写需要注入的bean
 */
//@Import(CommonConfig.class)
//@Import(CommonImportSelector.class)
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebApplication.class, args);
        CommonConfig commonConfig = context.getBean(CommonConfig.class);
    }
}
