package xyz.chaofan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("chat_user")
public class User {
  @TableId(type = IdType.ASSIGN_ID)
  private long id;
  private String openid;
  private long token;
}
