package org.springframework.social.uber.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
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
