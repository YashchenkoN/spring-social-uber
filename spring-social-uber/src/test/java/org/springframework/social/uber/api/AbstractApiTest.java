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
