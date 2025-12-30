package com.seleniumframeworkusingai.apis;

import com.seleniumframeworkusingai.base.BaseAPI;
import io.restassured.response.Response;
import java.util.Map;
import java.util.HashMap;

public class AuthAPI extends BaseAPI {

    private static final String VERIFY_LOGIN_ENDPOINT = "/api/verifyLogin";

    public Response verifyLogin(String email, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return postOrGet(VERIFY_LOGIN_ENDPOINT, "POST", null, body);
    }

    public Response verifyLoginMissingEmail(String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("password", password);
        return postOrGet(VERIFY_LOGIN_ENDPOINT, "POST", null, body);
    }
}
