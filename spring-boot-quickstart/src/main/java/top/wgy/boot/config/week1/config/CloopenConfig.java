package top.wgy.boot.config.week1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wgy.sms.ccp")
public class CloopenConfig {
    private String serverIp;
    private String port;
    private String accountSID;
    private String accountToken;
    private String appId;
    private String templateId;
}