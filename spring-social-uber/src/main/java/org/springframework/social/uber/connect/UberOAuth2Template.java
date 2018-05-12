/*
 * Copyright 2011-2018 the original author or authors.
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
package org.springframework.social.uber.connect;

import com.uber.sdk.core.client.SessionConfiguration;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author Mykola Yashchenko
 */
public class UberOAuth2Template extends OAuth2Template {

    public static final String AUTHORIZATION_PATH = "/oauth/v2/authorize";
    public static final String TOKEN_PATH = "/oauth/v2/token";

    public UberOAuth2Template(final String clientId, final String clientSecret,
                              final SessionConfiguration.EndpointRegion endpointRegion) {

        super(clientId, clientSecret,
                getLoginHost(endpointRegion) + AUTHORIZATION_PATH,
                getLoginHost(endpointRegion) + TOKEN_PATH
        );
    }

    public static String getLoginHost(final SessionConfiguration.EndpointRegion endpointRegion) {
        return String.format("https://login.%s", endpointRegion.getDomain());
    }
}
