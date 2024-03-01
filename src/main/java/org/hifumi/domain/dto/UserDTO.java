package org.hifumi.domain.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO用来跨服务传递数据
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ApiModel(description = "用户DTO")
public class UserDTO {

//    @ApiModelProperty("id")
    Long id;

//    @ApiModelProperty("用户名")
    String name;

//    @ApiModelProperty("密码")
    String password;
}
