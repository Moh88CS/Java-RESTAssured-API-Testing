package test.create;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardBodyValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardAuthValidationArgumentsProvider;
import arguments.providers.CardBodyValidationArgumentsProvider;
import consts.BoardsEndpoints;
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

public class CreateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardBodyValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidName(CardBodyValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .body(validationArguments.bodyParams())
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardAuthValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.authParams())
                .body(Map.of(
                        "idList", UrlParamValues.EXISTING_LIST_ID,
                        "name", "new card"))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @Test
    public void checkCreateCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .body(Map.of(
                        "idList", UrlParamValues.EXISTING_LIST_ID,
                        "name", "new card"))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid key", response.body().asString());
    }
}
