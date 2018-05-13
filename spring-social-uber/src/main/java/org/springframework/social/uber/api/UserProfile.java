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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User info.
 *
 * @author Mykola Yashchenko
 */
public class UserProfile {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String picture;
    private final String promoCode;
    private final String uuid;

    @JsonCreator
    public UserProfile(@JsonProperty("first_name") final String firstName,
                       @JsonProperty("last_name") final String lastName,
                       @JsonProperty("email") final String email,
                       @JsonProperty("picture") final String picture,
                       @JsonProperty("promo_code") final String promoCode,
                       @JsonProperty("uuid") String uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.promoCode = promoCode;
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public String getUuid() {
        return uuid;
    }
}
