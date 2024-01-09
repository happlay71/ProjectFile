package happlay.service.impl;

import happlay.entity.User;
import happlay.mapper.UserMapper;
import happlay.service.UserService;
import happlay.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service  // 标识一个类作为服务层的组件，会自动扫描、实例化、并管理这个服务类。
public class UserServiceImpl implements UserService {

    // 注入了UserMapper对象
    @Resource
    UserMapper userMapper;

    @Override
    public User select(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public R login(String username, String password) {
        if (username == null || username.isEmpty()) {
            return R.Error("用户名不能为空");
        }

        User user = userMapper.selectByUserName(username);
        if (user == null) {
            return R.Error("用户不存在");
        }
        if (user.getPassword().equals(password)) {
            return R.OK("登录成功");
        }
        return R.Error("密码错误");
    }

    @Override
    public R register(String username, String password) {
        if (username == null || username.isEmpty()) {
            return R.Error("用户名不能为空");
        }

        if (password == null || password.isEmpty()) {
            return R.Error("密码不能为空");
        }

        User oldUser = userMapper.selectByUserName(username);
        if (oldUser != null) {
            return R.Error("用户名存在");
        }

        // ！！！！！将数据封装成对象存入
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insertUser(user);

        return R.OK("注册成功");
    }

    @Override
    public R delete(String username) {
        if (username == null || username.isEmpty()) {
            return R.Error("用户名不能为空");
        }

        User user = userMapper.selectByUserName(username);
        if (user == null) {
            return R.Error("用户名不存在");
        }
        int users = userMapper.deleteUser(username);

        if(users == 0) {
            return R.Error("用户删除出错");
        }

        return R.OK("用户删除成功");
    }

    @Override
    public R update(String oldusername, String username, String password) {
        if (oldusername == null || oldusername.isEmpty()) {
            return R.Error("旧用户名不能为空");
        }

        if (username == null || username.isEmpty()) {
            return R.Error("新用户名不能为空");
        }

        if (password == null || password.isEmpty()) {
            return R.Error("新密码不能为空");
        }

        User oldUser = userMapper.selectByUserName(username);
        if (oldUser != null) {
            return R.Error("新用户名存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.updateUser(user, oldusername);

        return R.OK("用户修改成功");
    }
}
