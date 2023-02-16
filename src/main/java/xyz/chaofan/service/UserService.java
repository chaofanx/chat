package xyz.chaofan.service;

import java.util.Collections;
import java.util.List;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;
import xyz.chaofan.entity.User;
import xyz.chaofan.exception.NoSuchUserException;
import xyz.chaofan.mapper.UserMapper;

@Service
public class UserService {

  @Db
  private UserMapper userMapper;

  public int reduce(String openid) {
    List<User> user = userMapper.selectByMap(Collections.singletonMap("openid", openid));
    if (user.size() == 0) {
      throw new NoSuchUserException("please register first");
    }
    User u = user.get(0);
    u.setToken(u.getToken() - 1);
    return userMapper.updateById(u);
  }

}
