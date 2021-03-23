package cn.ywrby.test;

import cn.ywrby.domain.User;
import cn.ywrby.mapper.RoleMapper;
import cn.ywrby.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class UserTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void test(){
        User user=new User();
        user.setUsername("test");
        user.setEmail("test@testemail");
        user.setPassword("123456");
        user.setPhoneNum("18323300000");

        mapper.insert(user);

        System.out.println(user.getId());
    }
}
