package xyz.chaofan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.chaofan.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
