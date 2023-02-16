package xyz.chaofan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chat_user")

public class User {
  @TableId(type = IdType.ASSIGN_ID)
  private long id;

  /**
   * 微信用户openid
   */
  private String openid;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 性别 0 未知 1 男 2 女
   */
  private int gender;

  /**
   * 国家
   */
  private String country;

  /**
   * 省份
   */
  private String province;

  /**
   * 城市
   */
  private String city;

  /**
   * 注册时间
   */
  private long registerTime;

  /**
   * 电话
   */
  private String telephone;

  /**
   * 使用次数
   */
  private long token;
}
