package com.baily.template.demo.springboot;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@ConditionalOnProperty("druid.config.enabled")
@Component
public class SpringBootApolloRefreshConfig implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApolloRefreshConfig.class);

  @Autowired
  private DruidProperties druidProperties;

  @Autowired
  private RefreshScope refreshScope;

    private ApplicationContext applicationContext;

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
//      refreshByRefreshScope(changeEvent);
      refreshByRefreshScopeEvent(changeEvent);
  }

    private void refreshByRefreshScope(ConfigChangeEvent changeEvent) {
        LOGGER.info("before refresh {}", druidProperties.toString());
        refreshScope.refresh("druidProperties");
        LOGGER.info("after refresh {}", druidProperties.toString());
    }

    private void refreshByRefreshScopeEvent(ConfigChangeEvent changeEvent) {
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    }

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
