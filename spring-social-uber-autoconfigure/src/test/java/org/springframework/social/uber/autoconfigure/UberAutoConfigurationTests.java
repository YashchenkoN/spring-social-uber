package org.springframework.social.uber.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.social.autoconfigure.SocialWebAutoConfiguration;
import org.springframework.social.uber.api.Uber;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UberAutoConfiguration}.
 *
 * @author Mykola Yashchenko
 */
public class UberAutoConfigurationTests extends AbstractSocialAutoConfigurationTests {

    @Test
    public void expectedSocialBeansCreated() {
        this.context = new AnnotationConfigWebApplicationContext();
        TestPropertyValues.of("spring.social.uber.appId:12345").applyTo(this.context);
        TestPropertyValues.of("spring.social.uber.appSecret:secret").applyTo(this.context);
        ConfigurationPropertySources.attach(this.context.getEnvironment());
        this.context.register(UberAutoConfiguration.class);
        this.context.register(SocialWebAutoConfiguration.class);
        this.context.refresh();
        assertConnectionFrameworkBeans();
        assertThat(this.context.getBean(Uber.class)).isNotNull();
    }

    @Test
    public void noUberBeanCreatedWhenPropertiesArentSet() {
        this.context = new AnnotationConfigWebApplicationContext();
        this.context.register(UberAutoConfiguration.class);
        this.context.register(SocialWebAutoConfiguration.class);
        this.context.refresh();
        assertNoConnectionFrameworkBeans();
        assertMissingBean(Uber.class);
    }
}
