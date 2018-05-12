package org.springframework.social.uber.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.social.autoconfigure.SocialProperties;

/**
 * @author Mykola Yashchenko
 */
@ConfigurationProperties(prefix = "spring.social.uber")
public class UberProperties extends SocialProperties {
}
