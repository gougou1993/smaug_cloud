package smaug.cloud.provider.mappers.demo;


import org.apache.ibatis.annotations.Param;
import smaug.cloud.data.entity.demo.UserEntity;

import java.util.List;

public interface UserEntityMapper {

    List<UserEntity> getUserList();
    UserEntity getUser(@Param("id") int id);
}