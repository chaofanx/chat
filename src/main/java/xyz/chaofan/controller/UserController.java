package xyz.chaofan.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import xyz.chaofan.entity.User;

/**
 * 用户相关
 */
@Controller
public class UserController {

  @Post
  @Mapping("/login")
  public SaResult login(User user) {
    StpUtil.login(user.getId());
    return SaResult.ok("登录成功");
  }

  @Post
  @Mapping("/isLogin")
  public SaResult isLogin(User user) {
    return StpUtil.isLogin() ? SaResult.ok("已登录") : SaResult.error("未登录");
  }

  @Post
  @Mapping("/logout")
  public SaResult logout() {
    StpUtil.logout();
    return SaResult.ok();
  }

}
