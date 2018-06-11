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

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.uber.api.Uber;
import org.springframework.social.uber.api.impl.UberTemplate;

/**
 * @author Mykola Yashchenko
 */
public class UberServiceProvider extends AbstractOAuth2ServiceProvider<Uber> {

    private static final String DOMAIN = "uber.com";

    public UberServiceProvider(final String clientId, final String clientSecret) {
        super(new UberOAuth2Template(clientId, clientSecret, DOMAIN));
    }

    @Override
    public Uber getApi(final String accessToken) {
        return new UberTemplate(accessToken);
    }

}
