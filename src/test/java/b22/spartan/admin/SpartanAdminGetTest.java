package b22.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method
        baseURI = "http://3.86.103.110:7000";


    }

    @Test
    public void getAllSpartan(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);





    }


}
