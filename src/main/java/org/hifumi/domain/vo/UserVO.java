package org.hifumi.domain.vo;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hifumi.domain.pojo.UserInfo;
import org.hifumi.enums.UserStatus;

import java.time.LocalDateTime;

/**
 * VO用来把结果数据返回给前端
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ApiModel(description = "用户VO实体")
public class UserVO {

//    @ApiModelProperty("id")
    Long id;

//    @ApiModelProperty("用户名")
    String name;

//    @ApiModelProperty("密码")
    String password;

    LocalDateTime createTime;

    UserStatus status;

    UserInfo info;
}
