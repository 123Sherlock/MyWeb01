package org.hifumi.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommonImportSelector implements ImportSelector {

    /**
     * 读取配置中的所有第三方类并注入IoC容器
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> imports = new ArrayList<>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("common.imports");
        if (is == null) {
            System.err.println("resources目录下找不到common.imports文件");
            return new String[]{"org.hifumi.config.CommonConfig"};
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                imports.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imports.toArray(new String[0]);
    }
}
