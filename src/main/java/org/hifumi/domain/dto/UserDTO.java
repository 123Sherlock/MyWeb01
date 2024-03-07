package org.hifumi.domain.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hifumi.controller.UserController;

/**
 * DTO用来跨服务传递数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ApiModel(description = "用户DTO")
public class UserDTO extends BaseDTO {

//    @ApiModelProperty("id")
    /**
     * 当@Validated填写参数为UserDTO.Update.class时才校验@NotNull
     * @see UserController#update
     */
    @NotNull(groups = Update.class)
    Long id;

//    @ApiModelProperty("用户名")
    String username;

//    @ApiModelProperty("密码")
    String password;

    /**
     * 校验注解不指定groups时，属于Default
     */
    @Pattern(regexp = "^\\S{1,20}$")
    String nickname;

    /**
     * Email注解用来校验是否合法的邮件地址，需要配合@Validated使用
     * @see UserController#update
     */
    @Email
    String email;

    /**
     * 定义校验组
     * Default是当校验注解不指定groups时的默认组
     */
    public interface Add extends Default {

    }

    /**
     * 更新数据的校验组
     */
    public interface Update extends Default {

    }
}
