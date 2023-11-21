package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.UserLoginRequest;

import static client.BaseApi.getPostSpec;
import static client.BaseApi.getPostSpecAuth;
import static io.restassured.RestAssured.given;

public class UserApi {
    @Step("registration")
    public static ValidatableResponse registrationUser(CreateUserRequest user) {
        return given()
                .spec(getPostSpec())
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("authorization")
    public static ValidatableResponse authorizationUser(CreateUserRequest user) {
        return given()
                .spec(getPostSpec())
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }

    @Step("user delete")
    public static ValidatableResponse deleteUser(CreateUserRequest user, String token) {
        return given()
                .spec(getPostSpecAuth(token))
                .body(user)
                .when()
                .delete("/api/auth/user")
                .then();
    }

    @Step("change user with authorization")
    public static ValidatableResponse changeUserWithAuth(UserLoginRequest user, String token) {
        return given()
                .spec(getPostSpecAuth(token))
                .body(user)
                .when()
                .patch("/api/auth/user")
                .then();
    }

    @Step("change user without authorization")
    public static ValidatableResponse changeUserWithoutAuth(UserLoginRequest user) {
        return given()
                .spec(getPostSpec())
                .body(user)
                .when()
                .patch("/api/auth/user")
                .then();
    }
}