package org.hifumi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hifumi.domain.pojo.Result;
import org.hifumi.domain.pojo.User;
import org.hifumi.domain.vo.UserVO;

import java.util.List;

/**
 * MyBatis-Plus的IService预先实现好了很多常用操作，继承即可使用
 */
public interface UserService extends IService<User> {

    User findById(Long id);

    User findByName(String name);

    Result<?> register(String name, String password);

    Result<List<UserVO>> all();
}
