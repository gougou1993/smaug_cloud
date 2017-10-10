package smaug.cloud.provider.impl;

import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }
}
