package pf.bm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("auth0")
@Configuration
public class Config {

    @Value("${auth0.domain}")
    private String domain;
    private String managementClientId = "nl3w3BQvRpj2oZjfLr7QxNpjk8jn0nKl";
    private String managementClientSecret = "nWjTK_nvyz63BDKK99fa7bQCFgXkYu2oy3nOJpXX2yi9g_BDSoVZjglOp_rabTjo";
    private String audience = "https://dev-soa-asd.eu.auth0.com/api/v2/";
    private String loginScope = "openid profile";

    public String getDomain() {
        return domain;
    }

    public String getManagementClientId() {
        return managementClientId;
    }

    public String getManagementClientSecret() {
        return managementClientSecret;
    }

    public String getAudience() {
        return audience;
    }

    public String getLoginScope() {
        return loginScope;
    }
}
