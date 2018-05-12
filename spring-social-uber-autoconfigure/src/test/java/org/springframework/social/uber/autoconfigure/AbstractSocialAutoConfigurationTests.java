/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.uber.autoconfigure;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.fail;

/**
 * Abstract base class for testing Spring Social auto-configuration.
 *
 * @author Mykola Yashchenko
 */
public abstract class AbstractSocialAutoConfigurationTests {

    protected AnnotationConfigWebApplicationContext context;

    @AfterEach
    public void close() {
        if (context != null) {
            context.close();
        }
    }

    protected void assertConnectionFrameworkBeans() {
        assertThat(context.getBean(UsersConnectionRepository.class)).isNotNull();
        assertThat(context.getBean(ConnectionRepository.class)).isNotNull();
        assertThat(context.getBean(ConnectionFactoryLocator.class)).isNotNull();
        assertThat(context.getBean(UserIdSource.class)).isNotNull();
    }

    protected void assertNoConnectionFrameworkBeans() {
        assertMissingBean(UsersConnectionRepository.class);
        assertMissingBean(ConnectionRepository.class);
        assertMissingBean(ConnectionFactoryLocator.class);
        assertMissingBean(UserIdSource.class);
    }

    protected void assertMissingBean(final Class<?> beanClass) {
        try {
            assertThat(context.getBean(beanClass)).isNotNull();
            fail("Unexpected bean in context of type " + beanClass.getName());
        } catch (final NoSuchBeanDefinitionException ex) {
            // Expected
        }
    }

}