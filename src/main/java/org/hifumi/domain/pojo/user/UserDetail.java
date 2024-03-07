package org.hifumi.domain.pojo.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of") // 使用UserDetail.of()来构造对象
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetail {

    Integer age;

    Integer intro;
}
