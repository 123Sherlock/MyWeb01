package org.hifumi.config;

import org.springframework.context.annotation.Configuration;

/**
 * 通过Configuration注解，在本类中注入所有第三方bean
 */
@Configuration
public class CommonConfig {

    /**
     * 使用Bean注解把第三方对象作为bean注入Spring管理
     * 对象默认的名字是方法名，也可以通过@Bean("myName")来指定名字
     * 如果方法的内部需要用到其他bean，只需在方法的参数里加上即可，Spring会自动注入
     *
     * 如果需要读取配置里的值，可以在参数列表里加上@Value("$配置名")参数类型 参数名
     * 即thirdPartyComponent(@Value("$server.port")int port, @Value("$server.servlet.context-path")String path)
     *
     * ConditionalOnProperty注解表示如果配置文件中配置了指定的信息，则注入，否则不注入
     * ConditionalOnMissingBean注解表示如果不存在指定的bean，则注入，否则不注入
     * ConditionalOnClass注解表示如果当前环境中存在指定类，则注入，否则不注入
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "server", name = {"port", "servlet.context-path"})
//    @ConditionalOnMissingBean(CommonConfig.class)
//    @ConditionalOnClass(name = "org.hifumi.config.CommonConfig")
//    public ThirdPartyComponent thirdPartyComponent() {
//        return new ThirdPartyComponent();
//    }
}
