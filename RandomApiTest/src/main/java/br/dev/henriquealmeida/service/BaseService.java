package br.dev.henriquealmeida.service;

import br.dev.henriquealmeida.rest_assured.RequestSpecificationFactory;
import br.dev.henriquealmeida.rest_assured.RestClient;
import br.dev.henriquealmeida.rest_assured.RestClientImplementation;
import br.dev.henriquealmeida.util.UrlReader;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class BaseService {

    protected RestClient restClient;
    protected static final Map<String, Object> HEADER = Map.of("Content-type", ContentType.JSON);

    protected BaseService(String propertyName) {
        UrlReader urlReader = new UrlReader();
        RequestSpecification requestSpecification = RequestSpecificationFactory.of(urlReader.getUrl(propertyName));
        restClient = new RestClientImplementation(requestSpecification);
    }
}
