package org.hifumi.domain.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of") // 使用UserInfo.of()来构造对象
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {

    Integer age;

    Integer intro;
}
