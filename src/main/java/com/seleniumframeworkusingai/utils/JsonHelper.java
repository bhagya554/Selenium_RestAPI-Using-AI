package com.seleniumframeworkusingai.utils;

import io.restassured.path.json.JsonPath;

public class JsonHelper {

    /**
     * Cleans HTML tags (e.g. <pre>) from API responses and returns a valid JsonPath object.
     *
     * @param responseBody Raw response body possibly wrapped in HTML.
     * @return Parsed JsonPath object for valid JSON access.
     */
    public static JsonPath parseHtmlWrappedJson(String responseBody) {
        if (responseBody == null || responseBody.isEmpty()) {
            throw new IllegalArgumentException("Response body is empty!");
        }
        String clean = responseBody
                .replaceAll("(?s)<pre>|</pre>", "") // remove <pre> tags
                .trim();
        return new JsonPath(clean);
    }
}
