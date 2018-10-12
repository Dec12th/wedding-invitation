package com.baily.template.demo.springboot;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@ConditionalOnProperty("druid.config.enabled")
@Component
public class SpringBootApolloRefreshConfig {
  private static final Logger logger = LoggerFactory.getLogger(SpringBootApolloRefreshConfig.class);

  @Autowired
  private DruidProperties druidProperties;

  @Autowired
  private RefreshScope refreshScope;

  @ApolloConfigChangeListener
  public void onChange(ConfigChangeEvent changeEvent) {
    boolean druidConfigKeysChanged = false;
    for (String changedKey : changeEvent.changedKeys()) {
      if (changedKey.startsWith("druid.config")) {
        druidConfigKeysChanged = true;
        break;
      }
    }
    if (!druidConfigKeysChanged) {
      return;
    }

    logger.info("before refresh {}", druidProperties.toString());
    refreshScope.refresh("druidProperties");
    logger.info("after refresh {}", druidProperties.toString());
  }
}
