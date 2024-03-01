package org.hifumi.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hifumi.enums.UserStatus;

import java.time.LocalDateTime;

/**
 * 实体类，与数据库表名相同，或默认把驼峰命名转为下划线连接
 * 当表名与类名不同时，使用@TableName
 * TableName注解的autoResultMap = true表示自动构建结果集映射，用来处理JSON嵌套的情况
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName(autoResultMap = true)
public class User {

    /**
     * 默认主键叫id，如果不叫id，则使用@TableId
     * 可使用type = IdType.AUTO使id自增
     * 如果不指定，则默认type = IdType.ASSIGN_ID使用雪花算法生成id
     */
//    @TableId(value = "_id", type = IdType.AUTO)
    Long id;

    /**
     * 默认把驼峰命名转为下划线连接
     * 如果要自定义，则使用@TableField
     * 如果变量名是以is开头的布尔型，MyBatis-Plus会默认去掉is，如果不想如此，也需要使用@TableField
     * 建议POJO中所有布尔类型的变量都不要以is开头
     * 如果变量名是SQL语句中的关键字，则需要使用@TableField("`order`")
     * 如果这个字段不存在于数据库中，则需要使用@TableField(exist = false)
     */
//    @TableField("username")
    String name;

    String password;

    String icon;

    Integer score;

    LocalDateTime createTime;

    LocalDateTime updateTime;

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
    UserInfo info;
}
