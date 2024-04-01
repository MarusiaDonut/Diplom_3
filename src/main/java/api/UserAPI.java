package api;

import classes.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    final static private String PATH_TO_CREATE_USER = "/api/auth/register";
    final static private String PATH_TO_LOGIN_USER = "/api/auth/login";
    final static private String PATH_TO_LOGOUT_USER = "/api/auth/logout";
    final static private String PATH_TO_CHANGE_USER = "/api/auth/user";

    @Step("Send POST request create user to /api/auth/register")
    public Response sendPostRequestCreateUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(PATH_TO_CREATE_USER);
    }

    @Step ("Send POST request login user to /api/auth/login")
    public Response sendPostRequestLoginCourier(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(PATH_TO_LOGIN_USER);
    }

    @Step("Send DELETE request user to /api/auth/user")
    public Response sendDeleteRequestUser(String token) {
        return given()
                .auth().oauth2(token)
                .header("Content-type", "application/json")
                .delete(PATH_TO_CHANGE_USER);
    }
}


