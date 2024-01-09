package happlay.mapper;

import happlay.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 先在resources的mapper的xml中写sql语句
    User selectById(int id);

    User selectByUserName(String username);

    int insertUser(User user);  // int返回存入成功的条数

    int deleteUser(String username);

    void updateUser(@Param("user") User user, @Param("oldusername") String oldusername);  // 用于指定传递给 SQL 语句的参数名称的注解

}
