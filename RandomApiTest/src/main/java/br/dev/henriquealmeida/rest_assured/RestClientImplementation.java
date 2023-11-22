package br.dev.henriquealmeida.rest_assured;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestClientImplementation implements RestClient {

    private final RequestSpecification requestSpecification;

    public RestClientImplementation(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Override
    public Response get(String resource) {
        return given().spec(requestSpecification).get(resource);
    }

    @Override
    public Response get(String resource, Map<String, Object> header) {
        return given().spec(requestSpecification).headers(header).get(resource);
    }

    @Override
    public Response get(String resource, @Nullable Map<String, Object> header, Map<String, ?> parameters) {
        return given().spec(requestSpecification).headers(header).queryParams(parameters).get(resource);
    }

    @Override
    public Response post(String resource, Object bodyRequest) {
        return given().spec(requestSpecification).body(bodyRequest).post(resource);
    }

    @Override
    public Response post(String resource, @Nullable Map<String, Object> header, Object bodyRequest) {
        return given().spec(requestSpecification).headers(header).body(bodyRequest).post(resource);
    }

    @Override
    public Response put(String resource, Map<String, Object> header, Object bodyRequest) {
        return given().spec(requestSpecification).headers(header).body(bodyRequest).put(resource);
    }

    @Override
    public Response put(String resource, Object bodyRequest) {
        return given().spec(requestSpecification).body(bodyRequest).put(resource);
    }

    @Override
    public Response delete(String resource, @Nullable Map<String, Object> header) {
        return given().spec(requestSpecification).headers(header).delete(resource);
    }

    @Override
    public Response delete(String resource) {
        return given().spec(requestSpecification).delete(resource);
    }
}
