package org.springframework.social.uber.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Mykola Yashchenko
 */
public class UserApiTest extends AbstractApiTest {

    @Test
    public void shouldReturnCurrentUser() {
        mockServer.expect(requestTo(uberUrl("/v1.2/me")))
                .andExpect(method(GET))
                .andExpect(header("Authorization", "Bearer accessToken"))
                .andRespond(withSuccess(json("current-profile"), MediaType.APPLICATION_JSON));

        final UserProfile userProfile = uber.userProfile();
        assertThat(userProfile, notNullValue());
        assertThat(userProfile.getEmail(), equalTo("mail123@uber.com"));
        assertThat(userProfile.getFirstName(), equalTo("First Name"));
        assertThat(userProfile.getLastName(), equalTo("Last Name"));
        assertThat(userProfile.getPicture(), equalTo("https://uber.com/pic1.png"));
        assertThat(userProfile.getPromoCode(), equalTo("promo150"));
        assertThat(userProfile.getUuid(), equalTo("123-321"));
    }
}
