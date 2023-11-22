package br.dev.henriquealmeida.rest_assured;

import io.restassured.response.Response;

import java.util.Map;

public interface RestClient {

    Response get(String resource);

    Response get(String resource, Map<String, Object> header);

    Response get(String resource, Map<String, Object> header, Map<String, ?> parameters);

    Response post(String resource, Object bodyRequest);

    Response post(String resource, Map<String, Object> header, Object bodyRequest);

    Response put(String resource, Map<String, Object> header, Object bodyRequest);

    Response put(String resource, Object bodyRequest);

    Response delete(String resource, Map<String, Object> header);

    Response delete(String resource);
}
