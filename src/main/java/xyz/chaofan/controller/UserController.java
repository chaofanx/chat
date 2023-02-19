package xyz.chaofan.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import xyz.chaofan.entity.User;
import xyz.chaofan.service.UserService;

/**
 * 用户相关
 */
@Controller
public class UserController {

  @Inject
  private UserService userService;

  @Post
  @Mapping("/login")
  public SaResult login(User user) {
    user = userService.findUser(user);
    if (user.getId() == 0) {
      return SaResult.error("用户不存在");
    }
    StpUtil.login(user.getId());
    return SaResult.data(user.getId());
  }

  @Post
  @Mapping("/isLogin")
  public SaResult isLogin(User user) {
    return StpUtil.isLogin() ? SaResult.ok() : SaResult.error("not login");
  }

  @Post
  @Mapping("/logout")
  public SaResult logout() {
    StpUtil.logout();
    return SaResult.ok();
  }

}
