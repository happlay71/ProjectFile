package happlay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // 直接实现Get、Set、toString等方法
@NoArgsConstructor  // 提供类的无参构造
@AllArgsConstructor  // 提供类的全参构造
public class User {  // 使用Mybatis进行数据库操作时创建的实体类/数据对象, 设置数据库中的属性
    private int id;

    private String username;

    private String password;
}
