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
