package com.baily.template.common.config.db.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "druid.config")
@Data
public class DruidProperties {

    private String urlMapping;

    private String resetEnabl;

    private String allow;

    private String deny;

    private String loginUsername;

    private String loginPassword;

    private String urlPatterns;

    private String exclusions;

    private String profileEnable;

}
