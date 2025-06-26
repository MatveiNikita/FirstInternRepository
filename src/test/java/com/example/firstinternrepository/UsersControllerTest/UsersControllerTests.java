package com.example.firstinternrepository.UsersControllerTest;


import com.example.firstinternrepository.dto.CreateUserDTO;
import com.example.firstinternrepository.dto.UpdateUserDTO;
import com.example.firstinternrepository.model.User;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
public class UsersControllerTests {
    private static final String BASE_URI = "http://localhost:8080/users";

    @Test
    public void PostUserTest() {
        CreateUserDTO request = new CreateUserDTO("Mihau", "mihai@mail.com");

        User response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post()
                .then().extract()
                .as(User.class);

        assertThat(response)
                .isNotNull()
                .extracting(User::getUsername)
                .isEqualTo(request.username());
    }

    @Test
    public void GetUserTestById() {
        given()
                .baseUri(BASE_URI).
                queryParam("id", "68594d46fb8ac621ee3a9f97")
                .accept(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("username", is("Nocson"))
                .body("email", is("nic@mail.com"))
                .body("tasks.find{it.id=='68594d81fb8ac621ee3a9f98'}.description", is("today"));
    }

    @Test
    public void UpdateUsername() {
        CreateUserDTO request = new CreateUserDTO("OriginalName", "origemail@mail.com");

        User createResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post()
                .then().statusCode(201)
                .extract().as(User.class);

        User updateResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .queryParam("username", "NewUsername")
                .when()
                .patch("/" + createResponse.getId() + "/update-username")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getId()).isEqualTo(createResponse.getId());
        assertThat(updateResponse.getUsername()).isEqualTo("NewUsername");
    }

    @Test
    public void UpdateEmail() {
        CreateUserDTO request = new CreateUserDTO("EmailName", "current@maol.com");

        User createResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post()
                .then().statusCode(201)
                .extract().as(User.class);

        User updateResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .queryParam("email", "newemail@mail.com")
                .when()
                .patch("/" + createResponse.getId() + "/update-email")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getId()).isEqualTo(createResponse.getId());
        assertThat(updateResponse.getEmail()).isEqualTo("newemail@mail.com");
    }

    @Test
    public void UpdateUser(){
        CreateUserDTO request = new CreateUserDTO("LastName", "lastemail@mail.com");

        User createResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post()
                .then().statusCode(201)
                .extract().as(User.class);

        UpdateUserDTO updateRequest = new UpdateUserDTO("UpdateName", "updateemail@gmail.com");

        User updateResponse = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(updateRequest)
                .when()
                .patch("/" + createResponse.getId() + "/update-user")
                .then().statusCode(200)
                .extract().as(User.class);

        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getId()).isEqualTo(createResponse.getId());
        assertThat(updateResponse.getUsername()).isEqualTo(updateRequest.username());
        assertThat(updateResponse.getEmail()).isEqualTo(updateRequest.email());
    }

    @Test
    public void DeleteUser(){
        String deletedId = "68594dd7fb8ac621ee3a9f9b";
        String message = given()
                .baseUri(BASE_URI)
                .queryParam("id", deletedId)
                .when()
                .delete()
                .then()
                .extract()
                .asString();

        assertThat(message)
                .isNotNull()
                .contains("Get out here");
    }
}