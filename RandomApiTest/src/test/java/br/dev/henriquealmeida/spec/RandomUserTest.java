package br.dev.henriquealmeida.spec;

import br.dev.henriquealmeida.service.RandomUserService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomUserTest {

    private final RandomUserService randomUserService = RandomUserService.getInstance();

    @Test
    @DisplayName("Generate random user and check the status code is '200'")
    public void generateRandomUser() {
        randomUserService
                .getGenerateRandomUser()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
