package user;

import client.UserApi;
import io.qameta.allure.junit4.DisplayName;
import model.UserLoginRequest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class СhangeUserTest extends BaseUser {

    @Test
    @DisplayName("change authorization user /api/auth/user")
    public void changeAuthorizationUserTest() {
        userLoginRequest = UserLoginRequest.getRandomUser();

        response = UserApi.registrationUser(user);
        token = response.extract().path("accessToken");
        responseUpdate = UserApi.changeUserWithAuth(userLoginRequest, token);
        responseUpdate
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("change without authorization user /api/auth/user")
    public void changeWithoutAuthorizationUserTest() {
        userLoginRequest = UserLoginRequest.getRandomUser();

        response = UserApi.registrationUser(user);
        token = response.extract().path("accessToken");
        responseUpdate = UserApi.changeUserWithoutAuth(userLoginRequest);
        responseUpdate
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }
}