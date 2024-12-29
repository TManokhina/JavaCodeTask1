package in.reqres;

import in.reqres.api.client.UserClient;
import in.reqres.user.UserList;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.assertj.core.api.BDDAssertions.as;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;

public class GetListUsersTest extends SetUp {

    @Test
    @DisplayName("Проверка списка пользователей")
    @Description("Проверка возможности получения списка пользователей - запрос должен вернуть " +
            "200, email пользователей имеет окончание @reqres.in")
    public void checkGetListUsers() {

        Response getListUsersResponse = UserClient.getListUsers();
        assertEquals("Неверный статус код при получении списка пользователей", HTTP_OK,
                getListUsersResponse.statusCode());
        List<UserList.UserData> users = getListUsersResponse.as(UserList.class).getData();
        assertThatList(users)
                .allSatisfy(userData -> assertThat(userData)
                        .describedAs("В ответе содержится пустой пользователь")
                        .isNotNull()
                        .extracting(UserList.UserData::getEmail, as(STRING))
                        .describedAs("Email пользователя не из домена reqres")
                        .endsWith("@reqres.in"));
    }
}
