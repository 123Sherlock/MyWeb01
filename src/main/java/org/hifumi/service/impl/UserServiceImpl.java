package org.hifumi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.hifumi.domain.pojo.Result;
import org.hifumi.domain.pojo.User;
import org.hifumi.domain.vo.UserVO;
import org.hifumi.mapper.UserMapper;
import org.hifumi.service.UserService;
import org.hifumi.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 为了使用MyBatis-Plus的IService里的各种方法
 * 同时本类又不想把IService里的方法再实现一遍
 * 就需要继承ServiceImpl类
 * 泛型参数为Mapper类和POJO类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findById(Long id) {
//        return userMapper.findById(id); // MyBatis
        return userMapper.selectById(id); // MyBatis-Plus
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Result<?> register(String name, String password) {
        if (findByName(name) != null) {
            return Result.fail("用户名重复");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(MD5Util.encrypt(password));
        userMapper.insert(user);
//        userMapper.add(name, MD5Util.encrypt(password));
        return Result.success();
    }

    @Override
    public Result<List<UserVO>> all() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
            .select("id", "name", "icon")
            .like("name", "i")
            .ge("id", 1);

        // 由于QueryWrapper写死字段名，推荐改用LambdaQueryWrapper
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
            .select(User::getId, User::getName);
        List<User> users = userMapper.selectList(wrapper);

        // 仅更新指定字段
        User user = new User();
        user.setIcon("icon");
        userMapper.update(user, wrapper);

        // 复杂更新需要自定义SQL语句
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
            .setSql("score = score - 100")
            .in("id", List.of(1, 2));
        userMapper.update(updateWrapper);

        // 把Wrapper传到Mapper的SQL语句里执行
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda()
            .in(User::getId, 1, 2, 3);
        userMapper.updateScoreByIds(queryWrapper, 100);

        // 使用lambdaQuery比new Wrapper更方便
        String name = "i";
        List<User> userList = lambdaQuery()
            .like(StrUtil.isNotBlank(name), User::getName, name) // 当isNotBlank成立时，才启用此条件
            .gt(User::getId, 1)
            .between(User::getCreateTime, LocalDateTime.now(), LocalDateTime.now().plusDays(1))
            .list(); // 查一个时用one()，查数量时用count()

        // 使用lambdaUpdate更新数据
        lambdaUpdate()
            .set(User::getScore, user.getScore() - 100) // 更新score，对应SET语句
            .set(user.getScore() < 0, User::getIcon, "") // 当更新后的score < 0时，才执行此更新
            .eq(User::getId, 1) // WHERE条件
            .update(); // 只有调用时才真正执行更新

        // 可以使用静态工具创建lambdaQuery，在不依赖其他Service的情况下查询其他实体类
        Db.lambdaQuery(User.class);

        // 使用MyBatis-Plus的分页插件，在MyBatisConfig里初始化
        // 常用的分页插件还有PageHelper，据说更方便
        int pageNo = 1, pageSize = 10;
        Page<User> page = Page.of(pageNo, pageSize);
        page.addOrder(OrderItem.ascs("id", "name")); // 设置排序字段
        page(page, wrapper); // 分页查询
        page.getPages(); // 总页数

        return Result.success(BeanUtil.copyToList(users, UserVO.class));
    }

    @Autowired
    private UserMapper userMapper;
}
