package com.pduarteo.restful.users.api;

import org.springframework.http.MediaType;

public final class MediaTypesV1 {
    private MediaTypesV1() {}
    public static final String VND_USER_V1 = "application/vnd.pduarteo.user.v1+json";
    public static final MediaType VND_USER_V1_TYPE = MediaType.valueOf(VND_USER_V1);

}
