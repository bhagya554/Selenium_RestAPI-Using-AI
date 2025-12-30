package com.seleniumframeworkusingai.apis;

import com.seleniumframeworkusingai.base.BaseAPI;
import io.restassured.response.Response;

public class SearchAPI extends BaseAPI {

    private static final String SEARCH_ENDPOINT = "/api/searchProduct";

    /**
     * Search product with valid term.
     */
    public Response searchProduct(String searchTerm) {
        return givenRequest()
                .contentType("application/x-www-form-urlencoded") // ✅ fix content type
                .formParam("search_product", searchTerm)           // ✅ use form param
                .post(SEARCH_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Trigger search without the required parameter to validate error handling.
     */
    public Response searchProductMissingTerm() {
        return givenRequest()
                .contentType("application/x-www-form-urlencoded")
                .post(SEARCH_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
