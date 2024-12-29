package in.reqres.api.client;

import in.reqres.user.UserCredentials;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserClient implements Client {

    public static final String REGISTRATION_USER_PATH = "/api/register";
    public static final String LIST_USERS_PATH = "/api/users?page=2";
    public static final String DELETE_USER_PATH = "/api/users/";

    @Step("Отправка POST запроса для регистрации пользователя.")
    public static Response createUserResponse(UserCredentials userCredentials) {
        return given()
                .contentType(JSON)
                .and()
                .body(userCredentials)
                .post(REGISTRATION_USER_PATH);
    }

    @Step("Отправка GET запроса для получения списка пользователей.")
    public static Response getListUsers() {
        return given().get(LIST_USERS_PATH);
    }

    @Step("Отправка DELETE запроса для удаления пользователя.")
    public static Response deleteUser(Integer id) {
        return given().delete(DELETE_USER_PATH + id);
    }

}
