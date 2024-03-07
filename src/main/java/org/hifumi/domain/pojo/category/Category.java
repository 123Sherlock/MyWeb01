package org.hifumi.domain.pojo.category;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hifumi.domain.pojo.BasePojo;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BasePojo {

    Long id;

    String name;

    String alias;

    Long createUser;
}
