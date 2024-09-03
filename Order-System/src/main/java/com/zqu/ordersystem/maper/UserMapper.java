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

    // 按名称查询用户
    @Select("select * from users where username = #{username}")
    public User getUserByUsername(String username);

    // 添加一个用户
    @Insert("insert into users values (null, #{username}, #{password}, #{admin}, #{avatar}, #{address}, #{phone}, #{sex})")
    public Integer insertUser(User user);

    // 更新一个用户
    @Update("update users set username = #{username}, password = #{password}, admin = #{admin}, avatar = #{avatar}, address = #{address}, phone = #{phone}, sex = #{sex} where id = #{id}")
    public Integer updateUser(User user);

    // 删除一个用户
    @Delete("delete from users where id = #{id}")
    public Integer deleteUser(Integer id);

    // 按条件查询用户
    @Select("select * from users where username like concat('%', #{username}, '%')")
    List<User> selectByCondition(User user);

    // 注册一个用户
    @Insert("insert into users values (null, #{username}, #{password}, 0, null, null, null, null)")
    Integer registerUser(User user);
}
