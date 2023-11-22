package br.dev.henriquealmeida.rest_assured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationFactory {

    private RequestSpecificationFactory() {
    }

    public static RequestSpecification of(String baseUrl) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUrl);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecBuilder.addHeader("Content-type", String.valueOf(ContentType.JSON));
        return requestSpecBuilder.build();
    }
}
