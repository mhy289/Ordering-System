package com.zqu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //用户名
    private String username;
    //密码
    private String password;
    //管理权限
    private Boolean admin;

}
