package test.update;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.CardAuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class UpdateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) { // check record class
        Response response = requestWithAuth()
                .pathParams(validationArguments.pathParams())
                .body(Map.of("name", "updatedName"))
                .contentType(ContentType.JSON)
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.statusCode());
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardAuthValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.authParams())
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .body(Map.of("name", "updatedName"))
                .contentType(ContentType.JSON)
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .body(Map.of("name", "updatedName"))
                .contentType(ContentType.JSON)
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid key", response.body().asString());
    }
}
