package user;

import client.UserApi;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class LoginUserTest extends BaseUser {

    private static final String dataAuth = "somethingWrong";

    @Test
    @DisplayName("login test /api/auth/login")
    public void loginTest() {
        response = UserApi.registrationUser(user);
        token = response.extract().path("accessToken");
        login = UserApi.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("accessToken", is(notNullValue()));
    }

    @Test
    @DisplayName("Login with incorrect login field /api/auth/login")
    public void loginWithIncorrectLoginFieldTest() {
        response = UserApi.registrationUser(user);
        token = response.extract().path("accessToken");
        user.setEmail(user.getEmail() + dataAuth);
        login = UserApi.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Login with incorrect password field /api/auth/login")
    public void loginWithIncorrectPasswordFieldTest() {
        response = UserApi.registrationUser(user);
        token = response.extract().path("accessToken");
        user.setPassword(user.getPassword() + dataAuth);
        login = UserApi.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}