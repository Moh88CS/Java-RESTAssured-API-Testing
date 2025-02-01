package test;

import org.junit.jupiter.api.Test;

public class TrelloLogTest extends BaseTest {

    @Test
    public void checkTrelloApi() {
        requestWithoutAuth()
                .log().all()
                .get()
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
