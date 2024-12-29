package in.reqres;

import io.restassured.RestAssured;
import org.junit.Before;

import static in.reqres.api.client.Client.BASE_URI_PATH;

public class SetUp {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI_PATH;
    }

}
