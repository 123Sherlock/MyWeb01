package org.hifumi.service.impl;

import org.hifumi.enums.UserStatus;
import org.hifumi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Test
    void test() {
//        userService.listByIds(List.of(1, 2)).forEach(System.err::println);

        // 插入10万条数据，不能一次性插入，因为会new太多对象，以及一次性传到数据库的数据量有限，因此分批插入
        // 为了进一步提高批量插入的效率，要给MySQL配置rewriteBatchedStatements，具体查看application.yml
//        List<User> users = new ArrayList<>(1000);
//        for (int i = 1; i <= 100000; i++) { // i从1开始方便取余计算
//            users.add(new User());
//            if (i % 1000 == 0) {
//                userService.saveBatch(users);
//                users.clear();
//            }
//        }

        System.err.println(UserStatus.NORMAL);
    }

    @Autowired
    private UserService userService;
}
