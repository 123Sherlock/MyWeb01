package org.hifumi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hifumi.config.WebConfig;
import org.hifumi.utils.JWTUtil;
import org.hifumi.utils.PayloadMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 自定义拦截器，需要实现HandlerInterceptor接口
 * 另外还需要实现WebMvcConfigurer接口
 *
 * @see WebConfig
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 覆盖此方法，拦截在Controller之前执行
     *
     * @return true表示通过，false表示拒绝
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("auth");
            String redisToken = stringRedisTemplate.opsForValue().get(token);
            if (redisToken == null) {
                throw new RuntimeException("登陆token已失效");
            }
            Map<String, ?> payloadMap = JWTUtil.parseToken(token);
            PayloadMapUtil.set(payloadMap);
            return true;
        } catch (Exception e) {
            response.setStatus(401); // 修改响应的状态码
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完后清除ThreadLocal，防止内存泄漏
        PayloadMapUtil.remove();
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
}
