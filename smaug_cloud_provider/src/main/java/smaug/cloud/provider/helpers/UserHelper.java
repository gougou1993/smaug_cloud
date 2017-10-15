package smaug.cloud.provider.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smaug.cloud.data.entity.demo.UserEntity;
import smaug.cloud.provider.mappers.demo.UserEntityMapper;

import java.util.List;

/**
 * Created by naonao on 17/10/15.
 */
@Component("userHelper")
public class UserHelper {

    @Autowired
    private UserEntityMapper userEntityMapper;

    public List<UserEntity> getUserList() {
        return userEntityMapper.getUserList();
    }
}
