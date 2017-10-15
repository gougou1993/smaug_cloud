package smaug.cloud.provider.mappers.demo;


import smaug.cloud.data.entity.demo.UserEntity;

import java.util.List;

public interface UserEntityMapper {

    List<UserEntity> getUserList();
}