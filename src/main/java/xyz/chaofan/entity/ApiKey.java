package xyz.chaofan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("api_key")
public class ApiKey {
    @TableId(value = "apikey", type = IdType.INPUT)
    private String apikey;
}
