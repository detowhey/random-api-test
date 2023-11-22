package br.dev.henriquealmeida.service;

import io.restassured.response.Response;

public class RandomUserService extends BaseService {

    private static RandomUserService instance;
    private final String BASE_PATH = "api/";

    public RandomUserService() {
        super("random.user.url");
    }

    public static RandomUserService getInstance() {
        if (instance == null)
            instance = new RandomUserService();
        return instance;
    }

    public Response getGenerateRandomUser() {
        return restClient.get(BASE_PATH);
    }
}
