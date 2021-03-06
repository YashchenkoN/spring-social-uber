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
package org.springframework.social.uber.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.uber.api.Uber;
import org.springframework.social.uber.api.UserProfile;

/**
 * <p>This is the central class for interacting with Uber.</p>
 *
 * @author Mykola Yashchenko
 */
public class UberTemplate extends AbstractOAuth2ApiBinding implements Uber {

    private static final String ME_URI = "/v1.2/me";

    public UberTemplate(final String accessToken) {
        super(accessToken);
    }

    @Override
    protected OAuth2Version getOAuth2Version() {
        return OAuth2Version.BEARER;
    }

    @Override
    public UserProfile userProfile() {
        try {
            return getRestTemplate().getForObject(ME_URI, UserProfile.class);
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
