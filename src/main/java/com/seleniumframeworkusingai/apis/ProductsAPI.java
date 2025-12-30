package com.seleniumframeworkusingai.apis;

import com.seleniumframeworkusingai.base.BaseAPI;
import io.restassured.response.Response;

public class ProductsAPI extends BaseAPI {

    private static final String PRODUCTS_LIST_ENDPOINT = "/api/productsList";

    public Response getAllProducts() {
        return postOrGet(PRODUCTS_LIST_ENDPOINT, "GET", null, null);
    }

    public Response attemptPostProductsList(Object bodyPayload) {
        // According to docs this returns 405 — you may still call it to validate unsupported method
        return postOrGet(PRODUCTS_LIST_ENDPOINT, "POST", null, bodyPayload);
    }
}
