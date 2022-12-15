package b22.spartan.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @DisplayName("Editor should be able to post")
    @Test
    public void postSpartanAsEditor() {

        //create one spartan using util
        Map<String,Object> bodyMap = SpartanUtil.getRandomSpartanMap();

        System.out.println("bodyMap = " + bodyMap);

        //send a post request as editor


        given()
                .auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/spartans")
                .prettyPrint();

        /*
        status code is 201
        content type is Json
        success message is A Spartan is Born!
        id is not null
        name is correct
        gender is correct
        phone is correct
        check location header ends with newly generated id
*/
        Ensure.that("Status code is 201", x -> x.statusCode(201));

        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));

        Ensure.that("success message is correct",thenPart -> thenPart.body("success",is("A Spartan is Born!")));

        Ensure.that("id is not null",thenPart -> thenPart.body("data.id",notNullValue()));

        Ensure.that("name is correct",thenPart -> thenPart.body("data.name",is(bodyMap.get("name"))));

        Ensure.that("gender is correct",thenPart -> thenPart.body("data.gender",is(bodyMap.get("gender"))));

        Ensure.that("phone is correct", thenPart -> thenPart.body("data.phone",is(bodyMap.get("phone"))));

        String id = lastResponse().jsonPath().getString("data.id");

        Ensure.that("check location header ends with newly generated id", vR -> vR.header("Location",endsWith(id)));








    }

}
