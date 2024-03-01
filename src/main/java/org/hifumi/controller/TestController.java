package org.hifumi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController注解整合了Controller注解和ResponseBody注解
 * Controller注解相当于仅用于控制器类上的Component注解
 */
@RestController
public class TestController {

    /**
     * 通过localhost:8080/test访问
     * @return 返回值直接显示在浏览器
     */
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
