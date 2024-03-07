package org.hifumi.domain.pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hifumi.domain.pojo.BasePojo;
import org.hifumi.enums.UserStatus;

/**
 * 实体类，与数据库表名相同，或默认把驼峰命名转为下划线连接
 * 当表名与类名不同时，使用@TableName
 * TableName注解的autoResultMap = true表示自动构建结果集映射，用来处理JSON嵌套的情况
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName(autoResultMap = true)
public class User extends BasePojo {

    /**
     * 默认把驼峰命名转为下划线连接
     * 如果要自定义，则使用@TableField
     * 如果变量名是以is开头的布尔型，MyBatis-Plus会默认去掉is，如果不想如此，也需要使用@TableField
     * 建议POJO中所有布尔类型的变量都不要以is开头
     * 如果变量名是SQL语句中的关键字，则需要使用@TableField("`order`")
     * 如果这个字段不存在于数据库中，则需要使用@TableField(exist = false)
     */
//    @TableField("name")
    String username;

    /**
     * JsonIgnore注解让SpringMVC在把对象转换为JSON时忽略此字段
     */
    @JsonIgnore
    String password;

    String nickname;

    String icon;

    Integer score;

    /**
     * 使用枚举可以避免在查询时硬编码，在枚举里使用@EnumValue指定映射的字段
     */
    UserStatus status;

    /**
     * 数据库中的格式为JSON，如果要转换为实体类，则需要定义typeHandler
     * SpringMVC默认使用Jackson，所以使用JacksonTypeHandler
     * 还需要在本类加上注解@TableName(autoResultMap = true)
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    UserDetail detail;
}
