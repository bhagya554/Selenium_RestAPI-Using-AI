package com.seleniumframeworkusingai.base;

import com.seleniumframeworkusingai.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * BaseAPI - Core class providing reusable methods for API interactions.
 *
 * Best practices followed:
 * - Centralized Request/Response specs
 * - Config-driven base URI and timeouts
 * - Generic REST methods: GET, POST, PUT, DELETE
 * - Supports headers, path params, and body payloads
 */
public abstract class BaseAPI {

    protected static final RequestSpecification REQUEST_SPEC;
    protected static final ResponseSpecification RESPONSE_SPEC;

    static {
        String baseUri = ConfigReader.get("api.base.url");
        int timeoutSec = ConfigReader.getInt("api.timeout.seconds");

        // Configure RestAssured globally
        RestAssured.baseURI = baseUri;

        REQUEST_SPEC = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .log(LogDetail.ALL)
                .build();

        RESPONSE_SPEC = new ResponseSpecBuilder()

                .log(LogDetail.ALL)
                .build();

        // Optionally configure timeouts if needed using RestAssured.config()
    }

    /**
     * Prepares the base request specification for API calls.
     */
    protected RequestSpecification givenRequest() {
        return given().spec(REQUEST_SPEC);
    }

    /**
     * Common GET request with optional headers and query parameters.
     */
    protected Response get(String endpoint, Map<String, ?> headers, Map<String, ?> queryParams) {
        RequestSpecification request = givenRequest();

        if (headers != null) request.headers(headers);
        if (queryParams != null) request.queryParams(queryParams);

        return request
                .when()
                .get(endpoint)
                .then()
                .spec(RESPONSE_SPEC)
                .extract()
                .response();
    }

    /**
     * Common POST request with optional headers and JSON body.
     */
    protected Response post(String endpoint, Map<String, ?> headers, Object body) {
        RequestSpecification request = givenRequest();

        if (headers != null) request.headers(headers);
        if (body != null) request.body(body);

        return request
                .when()
                .post(endpoint)
                .then()
                .spec(RESPONSE_SPEC)
                .extract()
                .response();
    }

    /**
     * Common PUT request with optional headers and JSON body.
     */
    protected Response put(String endpoint, Map<String, ?> headers, Object body) {
        RequestSpecification request = givenRequest();

        if (headers != null) request.headers(headers);
        if (body != null) request.body(body);

        return request
                .when()
                .put(endpoint)
                .then()
                .spec(RESPONSE_SPEC)
                .extract()
                .response();
    }

    /**
     * Common DELETE request with optional headers.
     */
    protected Response delete(String endpoint, Map<String, ?> headers) {
        RequestSpecification request = givenRequest();

        if (headers != null) request.headers(headers);

        return request
                .when()
                .delete(endpoint)
                .then()
                .spec(RESPONSE_SPEC)
                .extract()
                .response();
    }

    /**
     * Simple utility to construct endpoint URLs.
     */
    protected String endpoint(String path) {
        return RestAssured.baseURI + path;
    }

    protected Response postOrGet(String endpoint, String method, Map<String, ?> headers, Object body) {
        RequestSpecification request = givenRequest();
        if (headers != null) {
            request.headers(headers);
        }
        if (body != null) {
            request.body(body);
        }

        Response response;
        switch (method.toUpperCase()) {
            case "GET":
                response = request.when().get(endpoint);
                break;
            case "POST":
                response = request.when().post(endpoint);
                break;
            case "PUT":
                response = request.when().put(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        return response.then()
                .spec(RESPONSE_SPEC)
                .extract()
                .response();
    }

}
