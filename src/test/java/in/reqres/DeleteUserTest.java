package in.reqres;

import in.reqres.api.client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static junit.framework.TestCase.assertEquals;

public class DeleteUserTest extends SetUp {
    static Integer id;

    @Test
    @DisplayName("Удаление пользователя")
    @Description("Проверка возможности удалить пользователя - запрос должен вернуть 204.")
    public void checkDeleteUser() {
        id = 2;
        Response deleteUserResponse = UserClient.deleteUser(id);

        assertEquals("Неверный статус код при регистрации пользователя", HTTP_NO_CONTENT,
                deleteUserResponse.statusCode());
    }
}
