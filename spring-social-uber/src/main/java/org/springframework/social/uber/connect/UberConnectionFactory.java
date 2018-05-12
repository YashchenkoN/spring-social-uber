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

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.uber.api.Uber;

/**
 * Uber {@link org.springframework.social.connect.ConnectionFactory} implementation.
 *
 * @author Mykola Yashchenko
 */
public class UberConnectionFactory extends OAuth2ConnectionFactory<Uber> {

    private static final String PROVIDER_ID = "uber";

    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param clientId     - id of application assigned by Uber
     * @param clientSecret - secret of application assigned by Uber
     */
    public UberConnectionFactory(String clientId, String clientSecret) {
        super(PROVIDER_ID, new UberServiceProvider(clientId, clientSecret), new UberAdapter());
    }
}
