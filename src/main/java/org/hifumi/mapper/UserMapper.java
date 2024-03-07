package org.hifumi.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hifumi.domain.pojo.user.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM `user` WHERE `username` = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO `user`(`username`, `password`, `create_time`) VALUES(#{username}, #{password}, now())")
    void add(String username, String password);

    /**
     * 在SQL中默认使用变量名作为字段名
     * 如果要自定义字段名，则使用@Param
     * 使用MyBatis-Plus来拼接SQL语句时，名字固定为ew
     * ${ew.customSqlSegment}表示拼接wrapper生成的SQL语句
     */
    @Update("UPDATE `user` SET `score` = `score` - #{score} ${ew.customSqlSegment}")
    void updateScoreByIds(@Param(Constants.WRAPPER) Wrapper<User> wrapper, int score);
}
