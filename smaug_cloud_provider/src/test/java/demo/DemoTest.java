package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smaug.cloud.common.utils.jsons.FastJsonUtil;
import smaug.cloud.provider.SmaugCloudApplication;
import smaug.cloud.provider.impl.TestServiceImpl;

/**
 * Created by naonao on 17/10/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmaugCloudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
public class DemoTest {
    Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Autowired
    private FastJsonUtil jsonUtil;

    @Autowired
    private TestServiceImpl testService;

    @Test
    public void testService() {
        String response = testService.test();
        logger.info(jsonUtil.toJson(response));
    }
}
