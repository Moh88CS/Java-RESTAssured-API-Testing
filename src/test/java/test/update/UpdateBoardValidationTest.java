package test.update;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.BoardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class UpdateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .put(BoardsEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.authParams())
                .pathParams("id", UrlParamValues.EXISTING_BOARD_ID)
                .body(Map.of("name", "updatedName"))
                .contentType(ContentType.JSON)
                .put(BoardsEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.errorMessage(), response.body().asString());
    }

    @Test
    public void checkUpdateBoardWithAnotherUserCredentials() { // assume an actual user cred
        Response response =requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParams("id", UrlParamValues.EXISTING_BOARD_ID)
                .body(Map.of("name", "updatedName"))
                .contentType(ContentType.JSON)
                .put(BoardsEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid key", response.body().asString());
    }
}
