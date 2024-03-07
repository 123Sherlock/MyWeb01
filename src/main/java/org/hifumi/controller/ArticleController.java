package org.hifumi.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.hifumi.domain.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    /**
     * @param token    @RequestHeader表示此参数从HTTP头中获取
     * @param response SpringMVC可以自动获取
     */
    @GetMapping("list")
    public Result<String> list(@RequestHeader("auth") String token, HttpServletResponse response) {
        return Result.success("list");
    }
}
