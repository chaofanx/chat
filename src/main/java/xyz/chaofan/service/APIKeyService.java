package xyz.chaofan.service;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;
import xyz.chaofan.mapper.ApiKeyMapper;
import xyz.chaofan.util.EncryptDecryptUtil;

@Service
public class APIKeyService {

    @Db
    private ApiKeyMapper apiKeyMapper;


    public String requestKey(Long id){
        //鉴权
        StpUtil.checkLogin();
        StpUtil.checkPermission("admin");
        //获取一个api-key
        String key = apiKeyMapper.selectList(null).get(0).getApikey();

        key = EncryptDecryptUtil.getInstance().decrypt(key);
        return key;
    }
}
