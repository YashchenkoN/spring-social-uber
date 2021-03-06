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

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.autoconfigure.SocialAutoConfigurerAdapter;
import org.springframework.social.autoconfigure.SocialWebAutoConfiguration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;
import org.springframework.social.uber.api.Uber;
import org.springframework.social.uber.connect.UberConnectionFactory;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Social connectivity with Uber.
 *
 * @author Mykola Yashchenko
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, UberConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.uber", name = "app-id")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class UberAutoConfiguration {

    @Configuration
    @EnableSocial
    @EnableConfigurationProperties(UberProperties.class)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    protected static class UberConfigurerAdapter extends SocialAutoConfigurerAdapter {

        private final UberProperties properties;

        protected UberConfigurerAdapter(final UberProperties properties) {
            this.properties = properties;
        }

        @Bean
        @ConditionalOnMissingBean(Uber.class)
        @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
        public Uber uber(final ConnectionRepository repository) {
            final Connection<Uber> connection = repository.findPrimaryConnection(Uber.class);
            return connection != null ? connection.getApi() : null;
        }

        @Bean(name = { "connect/uberConnect", "connect/uberConnected" })
        @ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
        public GenericConnectionStatusView uberConnectView() {
            return new GenericConnectionStatusView("uber", "Uber");
        }

        @Override
        protected ConnectionFactory<?> createConnectionFactory() {
            return new UberConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
        }

    }
}
