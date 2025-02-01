package test.get;

import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetListsTest extends BaseTest {

    @Test
    public void checkGetListsInABoard() {
        requestWithAuth()
                .pathParams("id", "670bfb5443e205163ecee4b2")
                .get("1/boards/{id}/lists")
                .then()
                .statusCode(200);
    }
}
