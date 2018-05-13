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
package org.springframework.social.uber.connect;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.uber.api.Uber;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mykola Yashchenko
 */
public class UberAdapterTest {

    private UberAdapter apiAdapter = new UberAdapter();
    private Uber uber = Mockito.mock(Uber.class);

    @Test
    public void shouldFetchProfile() {
        final org.springframework.social.uber.api.UserProfile userProfile = new org.springframework.social.uber.api.UserProfile(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        Mockito.when(uber.userProfile()).thenReturn(userProfile);

        final UserProfile profile = apiAdapter.fetchUserProfile(uber);
        assertThat(profile.getEmail(), equalTo(userProfile.getEmail()));
        assertThat(profile.getFirstName(), equalTo(userProfile.getFirstName()));
        assertThat(profile.getLastName(), equalTo(userProfile.getLastName()));
        assertThat(profile.getId(), equalTo(userProfile.getUuid()));
        assertThat(profile.getUsername(), equalTo(userProfile.getEmail()));
        assertThat(profile.getName(), equalTo(userProfile.getFirstName() + " " + userProfile.getLastName()));
    }
}
