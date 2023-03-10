package xyz.chaofan.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Collections;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import xyz.chaofan.entity.User;
import xyz.chaofan.exception.NoSuchUserException;
import xyz.chaofan.mapper.UserMapper;

@Service
public class UserService {

  @Db
  private UserMapper userMapper;

  public User findUser(User user) {
    var userList = userMapper.selectList(new QueryWrapper<>(user));
    Assert.isTrue(userList.size() == 1, "user not found");
    return userList.get(0);
  }

  @Tran
  public int reduce(Long openid) {
    var user = userMapper.selectByMap(Collections.singletonMap("id", openid));
    if (user.size() == 0) {
      throw new NoSuchUserException("please register first");
    }
    var u = user.get(0);
    u.setToken(u.getToken() - 1);
    return userMapper.updateById(u);
  }

}
