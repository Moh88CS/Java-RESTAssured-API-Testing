package test.delete;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

public class DeleteCardTest extends BaseTest {

    private String createdCardId;

    @BeforeEach
    public void createCard() {
        createdCardId = requestWithAuth()
                .body(Map.of(
                        "idList", UrlParamValues.EXISTING_LIST_ID,
                        "name", "Card for deletion"))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL)
                .body().jsonPath().get("id");
    }

    @Test
    public void checkDeleteCard() {
        requestWithAuth()
                .pathParams("id", createdCardId)
                .delete(CardsEndpoints.DELETE_CARD_URL)
                .then()
                .statusCode(200)
                .body("_value", Matchers.equalTo(null));
        requestWithAuth()
                .pathParams("id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .body("id", Matchers.not(Matchers.hasItem(createdCardId)));
    }
}
