package smaug.cloud.api.vos.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by naonao on 17/10/15.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserResponse {
    private int id;
    private String userName;
    private int age;
}
