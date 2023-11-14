package user;

import client.UserApi;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.UserLoginRequest;
import org.junit.After;
import org.junit.Before;

public class BaseUser {

    protected CreateUserRequest createUserRequest;
    protected UserLoginRequest userLoginRequest;
    protected CreateUserRequest user;
    protected String token;
    protected ValidatableResponse responseUpdate;
    protected ValidatableResponse responseTwice;
    protected ValidatableResponse response;
    protected ValidatableResponse login;


    @Before
    public void setup() {
        user = CreateUserRequest.getRandomUser();
    }

    @After
    public void cleanUp() {
        if (token == null) return;
        UserApi.deleteUser(user, token);
    }

    @Before
    public void setUp() {
        createUserRequest = CreateUserRequest.getRandomUser();
    }

    @After
    public void tearDown() {
        if (token == null) return;
        UserApi.deleteUser(createUserRequest, token);
    }
}
