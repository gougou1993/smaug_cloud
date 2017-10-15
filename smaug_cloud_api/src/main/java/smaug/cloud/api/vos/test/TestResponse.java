package smaug.cloud.api.vos.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by Allen on 17/10/10.
 */
@Data
public class TestResponse {
    private int id;
    private String name;
    @JSONField(serialize = false)
    private String address;

    private String druidType;
}
