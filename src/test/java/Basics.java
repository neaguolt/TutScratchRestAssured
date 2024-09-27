import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
import static utility.PayLoad.*;

public class Basics {
    private static final Logger logger = LoggerFactory.getLogger(Basics.class);

    public static void main(String[] args) {
        RestAssured.baseURI =  "https://rahulshettyacademy.com";
        logger.info("**baseURI set** {} \n", RestAssured.baseURI);
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        logger.info("\n{}", response);
    }
}