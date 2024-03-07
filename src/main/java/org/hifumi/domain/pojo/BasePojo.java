package org.hifumi.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BasePojo {

    /**
     * 默认主键叫id，如果不叫id，则使用@TableId
     * 可使用type = IdType.AUTO使id自增
     * 如果不指定，则默认type = IdType.ASSIGN_ID使用雪花算法生成id
     */
//    @TableId(value = "_id", type = IdType.AUTO)
    Long id;

    /**
     * JsonFormat注解可以指定实体类属性转换成JSON后的格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createTime;
}
