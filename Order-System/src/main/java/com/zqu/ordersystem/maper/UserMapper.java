package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 查询所有用户
    @Select("select * from users")
    public List<User> getAllUser();

    // 查询单个用户
    @Select("select * from users where id = #{id}")
    public User getUserById(Integer id);

    // 添加一个用户
    @Insert("insert into users values (null, #{username}, #{password}, #{admin}, #{avatar}, #{address}, #{phone}, #{sex})")
    public Integer insertUser(User user);

    // 更新一个用户
    @Update("update users set username = #{username}, password = #{password}, admin = #{admin}, avatar = #{avatar}, address = #{address}, phone = #{phone}, sex = #{sex} where id = #{id}")
    public Integer updateUser(User user);

    // 删除一个用户
    @Delete("delete from users where id = #{id}")
    public Integer deleteUser(Integer id);
}
