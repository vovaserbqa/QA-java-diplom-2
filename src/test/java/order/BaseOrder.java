package order;

import client.OrderApi;
import client.UserApi;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.OrderRequest;
import org.junit.After;
import org.junit.Before;

import java.util.List;

public class BaseOrder {

    protected CreateUserRequest createUserRequest;
    protected CreateUserRequest user;
    protected OrderRequest order;
    protected String token;
    protected ValidatableResponse responseCrt;
    protected ValidatableResponse response;


    @Before
    public void setUp() {
        createUserRequest = CreateUserRequest.getRandomUser();
        user = CreateUserRequest.getRandomUser();
        order = new OrderRequest();
    }

    @After
    public void tearDown() {
        if (token == null) return;
        UserApi.deleteUser(createUserRequest, token);
        if (token == null) return;
        UserApi.deleteUser(user, token);
    }
}
