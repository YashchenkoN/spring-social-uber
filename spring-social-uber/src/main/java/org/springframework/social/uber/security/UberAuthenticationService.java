package org.springframework.social.uber.security;

import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.uber.api.Uber;
import org.springframework.social.uber.connect.UberConnectionFactory;

/**
 * @author Mykola Yashchenko
 */
public class UberAuthenticationService extends OAuth2AuthenticationService<Uber> {

    public UberAuthenticationService(final String clientId, final String clientSecret) {
        super(new UberConnectionFactory(clientId, clientSecret));
    }
}
