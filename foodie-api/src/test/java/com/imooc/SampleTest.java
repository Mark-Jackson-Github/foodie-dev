package com.imooc;

import cn.hutool.crypto.SecureUtil;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testSelect() {
       log.info("aaa");
       log.debug("bbb");
    }

}