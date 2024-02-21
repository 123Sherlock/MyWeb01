package org.hifumi.aliyun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//使用统一前缀，此时属性名要跟配置里的一致
//@ConfigurationProperties(prefix = "ali-yun")
public class AliYunProperties {

    //映射application.yml文件中的配置
    @Value("${ali-yun.oss.bucket-name}")
    private String bucketName;
}
