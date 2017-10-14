package smaug.cloud.provider.impl;


import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.test.TestResponse;
import smaug.cloud.common.utils.jsons.FastJsonUtil;
import smaug.cloud.common.utils.jsons.JsonUtil;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {

    protected JsonUtil jsonUtil = new FastJsonUtil();

    @Override
    public String test() {
        return "test";
    }

    @Override
    public TestResponse getUser() {
        TestResponse response = new TestResponse();
        response.setId(1);
        response.setName("13");
        System.out.println(jsonUtil.toJson(response));
        return response;
    }
}
