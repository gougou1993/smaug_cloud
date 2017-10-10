package smaug.cloud.provider.impl;

import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.TestResponse;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }

    @Override
    public TestResponse getUser() {
        TestResponse response = new TestResponse();
        response.setId(1);
        response.setName("13");
        return response;
    }
}
