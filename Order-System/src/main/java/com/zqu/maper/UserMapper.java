package com.zqu.maper;

import com.zqu.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    // 查询所有用户
    @Select("select * from users")
    public List<User> getAllUser();

    // 查询单个用户
    @Select("select * from users where id = #{id}")
    public User getUserById(Integer id);

    // 添加一个用户
    @Insert("insert into users values (null, #{username}, #{password}, #{admin})")
    public void insertUser(User user);

    // 更新一个用户
    @Update("update users set username = #{username}, password = #{password}, admin = #{admin} where id = #{id}")
    public void updateUser(User user);

    // 删除一个用户
    @Delete("delete from users where id = #{id}")
    public void deleteUser(Integer id);
}
