package happlay.controller;

import happlay.entity.User;
import happlay.entity.constants.Constants;
import happlay.service.UserService;
import happlay.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")  //Spring MVC注解，将请求映射到控制器的处理方法，有请求时会调用这个类的方法进行处理
@RestController  // 指示这个类是一个控制器，可以处理 HTTP 请求，并且返回的数据是以 JSON 格式为主的
public class UserController {
    @Resource  // 将userServer这个类注入到这里
    UserService userService;

    @PostMapping("/getuser")
    public R getUser(int id) {
        User user = userService.select(id);
        Map<String, Object> result = new HashMap<>();  // 存储键值对
        result.put("id", user.getId());  // 存入值
        result.put("username", user.getUsername());
        return R.OK(result);
    }

    @PostMapping("/login")
    public R login(HttpSession session, String username, String password) {  // HttpSession是用于在Web应用中存储会话信息的对象
        R login = userService.login(username, password);  // userService进行登录验证
        if ((Integer)login.get("code") == 200) {
            session.setAttribute(Constants.SESSION_KEY, login.get("data"));  // 将登录成功的状态数据储存在session会话里，用Constants.SESSION_KEY作为会话的键
        }
        return login;  // 返回JSON格式
    }

    @PostMapping("/register")
    public R register(HttpSession session, String username, String password) {
        R register = userService.register(username, password);
        if ((Integer)register.get("code") == 200) {
            session.setAttribute(Constants.SESSION_KEY, register.get("data"));
        }
        return register;
    }

    @PostMapping("/delete")
    public R delete(HttpSession session, String username) {
        if (session.getAttribute("yourSessionAttributeName") != null) {
            R delete = userService.delete(username);
            if ((Integer)delete.get("code") == 200) {
                session.invalidate();
            }
            return delete;
        } else {
            return R.Error("未登录或注册");
        }
    }

    @PostMapping("/update")
    public R update(HttpSession session, String oldusername, String username, String password) {
        if (session.getAttribute("yourSessionAttributeName") != null) {
            R update = userService.update(oldusername, username, password);
            if ((Integer)update.get("code") == 200) {
                session.setAttribute(Constants.SESSION_KEY, update.get("data"));
            }
            return update;
        } else {
            return R.Error("未登录或注册");
        }

    }
}
