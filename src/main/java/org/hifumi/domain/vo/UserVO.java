package org.hifumi.domain.vo;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hifumi.domain.pojo.user.UserDetail;
import org.hifumi.enums.UserStatus;

import java.time.LocalDateTime;

/**
 * VO用来把结果数据返回给前端
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ApiModel(description = "用户VO实体")
public class UserVO extends BaseVO {

//    @ApiModelProperty("id")
    Long id;

//    @ApiModelProperty("用户名")
    String name;

//    @ApiModelProperty("密码")
    String password;

    LocalDateTime createTime;

    UserStatus status;

    UserDetail detail;
}
