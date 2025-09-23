package com.mes.api.requests;
import com.mes.utils.ConfigReader;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseApi {

    protected String baseApiHost = ConfigReader.getProperty("baseApiHost");
    protected String token = ConfigReader.getProperty("token");

    protected RequestSpecification request(String token) {
        return given()
                .baseUri(baseApiHost)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }
}
