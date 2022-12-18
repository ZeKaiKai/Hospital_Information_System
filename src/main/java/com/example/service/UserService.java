package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.util.sqlSessionFactoryTool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private SqlSessionFactory sqlSessionFactory = sqlSessionFactoryTool.getSqlSessionFactory();

    /**
    * 登录方法
    * @param username
    * @param password
    * @return
    * */
    public User login(String username, String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User select = mapper.select(username, password);
        return select;
    }

    /**
     * 注册方法
     * 功能：检查注册的用户是否存在，不存在则成功注册，录入数据库，否则返回报错
     * @return */
    public boolean register(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(user.getUsername());

        if(u == null){
            //如果用户不存在，可以注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }
}
