package smaug.cloud.common.consts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.activemq")
@Getter
@Setter
@Component
public class MQConst {
    public String smaugCommonQueue;
}
