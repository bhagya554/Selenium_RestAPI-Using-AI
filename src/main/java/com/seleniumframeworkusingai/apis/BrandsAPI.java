package com.seleniumframeworkusingai.apis;

import com.seleniumframeworkusingai.base.BaseAPI;
import io.restassured.response.Response;

public class BrandsAPI extends BaseAPI {

    private static final String BRANDS_LIST_ENDPOINT = "/api/brandsList";

    public Response getAllBrands() {
        return postOrGet(BRANDS_LIST_ENDPOINT, "GET", null, null);
    }

    public Response attemptPutBrandsList(Object bodyPayload) {
        // This method returns 405 (not supported) per documentation
        return postOrGet(BRANDS_LIST_ENDPOINT, "PUT", null, bodyPayload);
    }
}
