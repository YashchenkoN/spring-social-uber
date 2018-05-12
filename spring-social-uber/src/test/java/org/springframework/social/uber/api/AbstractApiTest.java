package org.springframework.social.uber.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.social.uber.api.impl.UberTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 * @author Mykola Yashchenko
 */
public class AbstractApiTest {
    private static final String ACCESS_TOKEN = "accessToken";

    protected UberTemplate uber;
    protected MockRestServiceServer mockServer;

    @BeforeEach
    public void setup() {
        uber = new UberTemplate(ACCESS_TOKEN);
        mockServer = MockRestServiceServer.createServer(uber.getRestTemplate());
    }

    protected Resource json(final String filename) {
        return new ClassPathResource(filename + ".json");
    }

    protected String uberUrl(final String uri) {
        return uber.getRestTemplate().getUriTemplateHandler().expand(uri).toString();
    }
}
