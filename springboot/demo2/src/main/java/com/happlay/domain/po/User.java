package com.happlay.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.happlay.enums.Userstatus;
import lombok.Data;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Data
@TableName(value = "tb_user", autoResultMap = true)  // 指定表名，自动结果集映射
//@TableName(value = "tb_user")  // 指定表名
public class User {

    /**
     * 用户id
     */
//    @TableId(type = IdType.AUTO)  // 自增
    private Long id;

    /**
     * 用户名
     */
//    @TableField("`username`")
    private String username;

    /**
     * 密码
     */
//    @TableField(exist = false)  // 成员变量不是数据库字段
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 详细信息
     */
//    private String info;
    @TableField(typeHandler = JacksonTypeHandler.class)  // 设置处理器
    private UserInfo info;

    /**
     * 使用状态（1正常 2冻结）
     */
//    private Integer status;
    private Userstatus status;

    /**
     * 账户余额
     */
    private Integer balance;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
