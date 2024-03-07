package org.hifumi.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.hifumi.domain.dto.UserDTO;
import org.hifumi.domain.pojo.Result;
import org.hifumi.domain.pojo.user.User;
import org.hifumi.domain.query.UserQuery;
import org.hifumi.domain.vo.UserVO;
import org.hifumi.exception.GlobalExceptionHandler;
import org.hifumi.service.UserService;
import org.hifumi.utils.JWTUtil;
import org.hifumi.utils.MD5Util;
import org.hifumi.utils.PayloadMapUtil;
import org.hifumi.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Api开头的注解都是Swagger用来生成RESTful风格的接口文档的
 * 访问地址为localhost:8080/doc.html
 * Swagger不支持SpringBoot3，可以考虑改用springdoc-openapi-starter-webmvc-ui
 * <p>
 * RestController注解是@ResponseBody和@Controller的组合注解
 * ResponseBody表示把方法返回的对象转换为JSON格式，写入到Response的Body中，可在浏览器中查看到
 * <p>
 * RequestMapping注解为本类所有方法加上统一的URL前缀
 */
//@Api(tags = "用户管理接口")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * localhost:8080/user/findById?id=1
     * RequestMapping注解不建议用在方法上，应该换用具体的@GetMapping或@PostMapping等
     */
    @RequestMapping("/findById")
    public UserVO findById(Long id) {
        User user = userService.findById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    /**
     * Pattern注解用来校验参数合法性
     * 需要引入spring-boot-starter-validation并在类上添加注解@Validated
     * 当参数合法性校验不通过时，会抛出ConstraintViolationException
     * 为了不给前端返回500错误，可自定义ExceptionHandler
     *
     * @see GlobalExceptionHandler
     */
    @PostMapping("/register")
    public Result<?> register(@Pattern(regexp = "^\\S{5,16}$") String username,
        @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.register(username, password);
    }

    /**
     * RequestBody注解表示以JSON格式接收参数
     * Validated注解用来校验UserDTO类里的成员变量，具体查看其成员变量上的注解
     */
//    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public void update(@RequestBody @Validated(UserDTO.Update.class) UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        userService.saveOrUpdate(user);
    }

    /**
     * Mapping注解里使用{id}表示读取URL上的值
     * PathVariable注解表示使用URL上读取到的id
     */
//    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    public void delete(/*@ApiParam("用户id")*/ @PathVariable("id") Long id) {
        userService.removeById(id);
    }

    /**
     * RequestParam注解默认required = true，即该参数必传，不用此注解则非必传
     * RequestParam可以指定参数名，也可以使用defaultValue设置默认值
     */
//    @ApiOperation("批量查询用户")
    @GetMapping("/findByIds")
    public List<UserVO> findByIds(/*@ApiParam("用户id集合")*/ @RequestParam("ids") List<Long> ids) {
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

//    @ApiOperation("扣减用户积分")
    @PutMapping("/{id}/deduct/{score}")
    public void deductScore(@PathVariable("id") Long id, @PathVariable("score") int score) {
        userService.all();
    }

    /**
     * get方式请求的对象，前端通过form提交参数，方法参数前不需加@RequestBody
     */
    @GetMapping("/queryUsers")
    public Result<List<UserVO>> queryUsers(@Validated UserQuery userQuery) {
        return userService.all();
    }

    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        User user = userService.findByName(username);
        if (user == null) {
            return Result.fail("用户名错误", StrUtil.EMPTY);
        }
        if (StrUtil.equals(user.getPassword(), MD5Util.encrypt(password))) {
            Map<String, Object> payloadMap = Map.of("id", user.getId(), "username", user.getUsername());
            String token = JWTUtil.genToken(payloadMap);

            // 把生成的token保存到Redis中，再在LoginInterceptor中验证
            stringRedisTemplate.opsForValue().set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.fail("密码错误", StrUtil.EMPTY);
    }

    @PatchMapping("/updatePassword")
    public Result<?> updatePassword(@RequestParam String oldPwd,
        @RequestParam String newPwd, @RequestHeader("auth") String token) {
        User user = userService.getById(UserUtil.getId());
        if (StrUtil.equals(user.getPassword(), MD5Util.encrypt(oldPwd))) {
            userService.lambdaUpdate()
                .set(StrUtil.isNotBlank(newPwd), User::getPassword, newPwd)
                .update();

            // 修改密码后，使旧的登陆token失效
            stringRedisTemplate.opsForValue().getOperations().delete(token);
            return Result.success();
        }
        return Result.fail("原密码错误");
    }

    @GetMapping("/info")
    public Result<UserVO> info() {
        String username = PayloadMapUtil.get("username");
        User user = userService.findByName(username);
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        return Result.success(userVO);
    }

    /**
     * PatchMapping在局部更新时使用
     * URL注解用来校验参数为URL格式
     */
    @PatchMapping("/updateIcon")
    public Result<?> updateIcon(@RequestParam @URL String iconURL) {
        userService.lambdaUpdate()
            .eq(User::getId, UserUtil.getId())
            .set(User::getIcon, iconURL)
            .update();
        return Result.success();
    }

    @Autowired
    private UserService userService;

    /**
     * 引入spring-boot-starter-data-redis依赖后，StringRedisTemplate会自动注入到Spring容器中，可以直接使用
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
}
