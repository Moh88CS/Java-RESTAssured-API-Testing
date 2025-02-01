package test.create;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

public class CreateCardTest extends BaseTest {

    private String createdCardId;

    @Test
    public void CheckCreateCard() {
        String cardName = "New Card" + LocalDateTime.now();
        Response response = requestWithAuth()
                .body(Map.of(
                        "idList", UrlParamValues.EXISTING_LIST_ID,
                        "name", cardName))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        createdCardId = response.body().jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(cardName));
        requestWithAuth()
                .pathParams("id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .body("name", Matchers.hasItem(cardName));
    }

    @AfterEach
    public void deleteCreatedCard() {
        requestWithAuth()
                .pathParams("id", createdCardId)
                .delete(CardsEndpoints.DELETE_CARD_URL)
                .then()
                .statusCode(200);
    }
}
