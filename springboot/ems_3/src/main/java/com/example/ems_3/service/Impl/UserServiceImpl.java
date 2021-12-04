package com.example.ems_3.service.Impl;

import com.example.ems_3.entity.User;
import com.example.ems_3.mapper.UserMapper;
import com.example.ems_3.service.UserService;
import com.example.ems_3.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void reg(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IsEmptyException("用户名或密码为空");
        }
        User result = userMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicateException("尝试注册的用户名[" + username + "]已经被占用");
        }
        Integer rows = userMapper.insert(username, password);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    /*没有调用这个,没有用上*/
    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        if (!result.getPassword().equals(password)) {
            throw new PasswordNotMatchException("密码验证失败的错误");
        }
        User user = new User();
        user.setId(result.getId());
        user.setUsername(result.getUsername());
        return user;
    }


    @Override
    public User findById(Integer id) {
        User result = userMapper.findById(id);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        return result;
    }

    /*
        @Override
        public void changeInfo(Integer id, String username, User user) {
            User result = userMapper.findById(id);
            if (result == null) {
                throw new UserNotFoundException("用户数据不存在");
            }

            user.setId(id);
            Integer rows = userMapper.updateInfoById(user);
            if (rows != 1) {
                // 是：抛出UpdateException异常
                throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
            }
        }
    */
    @Override
    public void changeInfo(User user) {
        User result = userMapper.findById(user.getId());
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows1 = userMapper.updateInfoById1(user.getId(), user.getUsername());
        Integer rows2 = userMapper.updateInfoById2(user.getId(), user.getSex());
        Integer rows3 = userMapper.updateInfoById3(user.getId(), user.getAge());
        Integer rows4 = userMapper.updateInfoById4(user.getId(), user.getNum());

        if (rows1 != 1 || rows2 != 1 || rows3 != 1 || rows4 != 1) {
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }

/*
        User user1=new User();
        user1.setId(user.getId());
        user1.setPassword(user.getPassword());
        user1.setUimg(user.getUimg());
        user1.setUsername(user.getUsername());
        user1.setSex(user.getSex());
        user1.setAge(user.getAge());
        user1.setNum(user.getNum());


        return user1;
*/
    }

    @Override
    public void changeAvatar(Integer uid, String uimg) {
        userMapper.changeAvatar(uid,uimg);
    }

    @Override
    public String findUimgByUid(Integer uid) {
        User user= userMapper.findById(uid);
        return user.getUimg();
    }

    /*
    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public User searchuserid(String username) {
        return userMapper.searchuserid(username);
    }

    @Override
    public void register(String username, String password*/
    /*, String sex, Integer age, Integer num, String uimg*//*
) {
        userMapper.register(username,password*/
    /*,sex,age,num,uimg*//*
);
    }

*/

    //    @Override
//    public void update(User user) {
//        userMapper.update(user);
//    }
//
//    @Override
//    public void register(User user) {
//        user.setId(UUID.randomUUID().toString());
//        userMapper.register(user);
//    }

//    @Override
//    public void showDetails() {
//        userMapper.showDetails();
//    }
}
