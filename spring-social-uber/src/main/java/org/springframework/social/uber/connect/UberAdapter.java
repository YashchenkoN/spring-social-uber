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

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.uber.api.Uber;

/**
 * Uber {@link ApiAdapter} implementation.
 */
public class UberAdapter implements ApiAdapter<Uber> {

    @Override
    public boolean test(final Uber api) {
        try {
            api.userProfile();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(final Uber api, final ConnectionValues values) {
        final org.springframework.social.uber.api.UserProfile uberProfile = api.userProfile();
        values.setProviderUserId(uberProfile.getUuid());
        values.setDisplayName(uberProfile.getFirstName() + " " + uberProfile.getLastName());
        values.setImageUrl(uberProfile.getPicture());
    }

    @Override
    public UserProfile fetchUserProfile(final Uber api) {
        final org.springframework.social.uber.api.UserProfile uberProfile = api.userProfile();

        return new UserProfileBuilder()
                .setId(uberProfile.getUuid())
                .setEmail(uberProfile.getEmail())
                .setFirstName(uberProfile.getFirstName())
                .setLastName(uberProfile.getLastName())
                .setName(uberProfile.getFirstName() + " " + uberProfile.getLastName())
                .setUsername(uberProfile.getEmail())
                .build();
    }

    @Override
    public void updateStatus(final Uber api, final String message) {

    }
}
