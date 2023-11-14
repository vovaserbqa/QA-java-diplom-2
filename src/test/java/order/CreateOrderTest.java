package order;

import client.OrderApi;
import client.UserApi;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.OrderRequest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest extends BaseOrder {

    @Test
    @DisplayName("creating an order with ingredients and with authorization /api/orders")
    public void orderIngredientsAuthorizationTest() {
        order = new OrderRequest(ingredientList());
        response = UserApi.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        responseCrt = OrderApi.createOrderWithAuth(token, order);
        responseCrt
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("creating an order with ingredients and without authorization /api/orders")
    public void orderIngredientsWithoutAuthorizationTest() {
        order = new OrderRequest(ingredientList());
        responseCrt = OrderApi.createOrderWithoutAuth(order);
        responseCrt
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("creating an order without ingredients and with authorization /api/orders")
    public void orderWithoutIngredientsAuthorizationTest() {
        response = UserApi.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        responseCrt = OrderApi.createOrderWithAuth(token, order);
        responseCrt
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("creating an order with an bad ingredient hash /api/orders")
    public void orderWithBadIngredientHashTest() {
        order = new OrderRequest(badIngredientList());
        response = UserApi.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        responseCrt = OrderApi.createOrderWithAuth(token, order);
        responseCrt
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Step("get ingredients list")
    protected List<String> ingredientList() {
        response = OrderApi.getIngredients();
        List<String> list = response.extract().path("data._id");
        List<String> ingredients = List.of(list.get(0), list.get(2), list.get(4), list.get(0));
        return ingredients;
    }

    @Step("get bad ingredients list")
    protected List<String> badIngredientList() {
        response = OrderApi.getIngredients();
        List<String> list = response.extract().path("data._id");
        List<String> ingredients = List.of(list.get(0), list.get(2).repeat(2), list.get(4).repeat(1), list.get(0));
        return ingredients;
    }
}