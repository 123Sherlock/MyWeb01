package org.hifumi.controller;

import cn.hutool.core.bean.BeanUtil;
import jakarta.validation.constraints.Pattern;
import org.hifumi.domain.dto.UserDTO;
import org.hifumi.domain.pojo.Result;
import org.hifumi.domain.pojo.User;
import org.hifumi.domain.query.UserQuery;
import org.hifumi.domain.vo.UserVO;
import org.hifumi.exception.GlobalExceptionHandler;
import org.hifumi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Api开头的注解都是Swagger用来生成RESTful风格的接口文档的
 * 访问地址为localhost:8080/doc.html
 * Swagger不支持SpringBoot3，可以考虑改用springdoc-openapi-starter-webmvc-ui
 *
 * RestController注解是@ResponseBody和@Controller的组合注解
 * ResponseBody表示把方法返回的对象转换为JSON格式，写入到Response的Body中，可在浏览器中查看到
 *
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
     * @see GlobalExceptionHandler
     */
    @PostMapping("/register")
    public Result<?> register(@Pattern(regexp = "^\\S{5,16}$") String name, @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.register(name, password);
    }

    /**
     * RequestBody注解表示以JSON格式接收参数
     */
//    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public void update(@RequestBody UserDTO userDTO) {
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
    @GetMapping("queryUsers")
    public Result<List<UserVO>> queryUsers(UserQuery userQuery) {
        return userService.all();
    }

    @Autowired
    private UserService userService;
}
