package in.reqres;

import in.reqres.api.client.UserClient;
import in.reqres.user.UserAuthData;
import in.reqres.user.UserCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class CreateUserTest extends SetUp {
    static String email;
    static String password;

    @Test
    @DisplayName("Создание пользователя")
    @Description("Проверка возможности создать пользователя при заполнении необходимых полей: имэйл, пароль - запрос " +
            "должен " +
            "вернуть 200.")
    public void checkCreationUser() {
        email = "eve.holt@reqres.in";
        password = "pistol";

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.withEmail(email);
        userCredentials.withPassword(password);
        Response createUserResponse = UserClient.createUserResponse(userCredentials);

        assertEquals("Неверный статус код при регистрации пользователя", HTTP_OK,
                createUserResponse.statusCode());
        UserAuthData userAuthData = createUserResponse.as(UserAuthData.class);
        assertNotNull("Тело ответа пустое", userAuthData);
        assertNotNull("В тело ответа не вернулся ID пользователя", userAuthData.getId());
        assertNotNull("В тело ответа не вернулся токен", userAuthData.getToken());
    }

    @Test
    @DisplayName("Создание пользователя без пароля")
    @Description("Проверка отсутствия возможности создать пользователя без указания пароля - запрос должен вернуть " +
            "400.")
    public void checkCreationUserWithoutPassword() {
        email = "eve.holt@reqres.in";

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.withEmail(email);

        Response createUserResponse = UserClient.createUserResponse(userCredentials);

        assertEquals("Неверный статус код при создании пользователя без пароля", HTTP_BAD_REQUEST,
                createUserResponse.statusCode());
    }


}
