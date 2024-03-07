package org.hifumi.domain.query;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hifumi.annotation.validate.CheckUserStatus;

/**
 * Query类封装前端发起查询请求时发送的参数
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQuery {

    Long id;

    String name;

    String password;

    @CheckUserStatus
    Integer status;
}
