package test.delete;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.CardAuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

public class DeleteCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.pathParams())
                .delete(CardsEndpoints.DELETE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.statusCode());
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardAuthValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.authParams())
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .delete(CardsEndpoints.DELETE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @Test
    public void checkDeleteCardWithAnotherUserCredentials() { // assume an actual user cred
        Response response =requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .delete(CardsEndpoints.DELETE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid key", response.body().asString());
    }
}
