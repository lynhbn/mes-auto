package com.mes.api.requests;
import com.mes.utils.OverrideUtils;
import com.mes.utils.RequestBodyBuilder;
import io.restassured.response.Response;

public class CustomerService extends BaseApi {

    private final String basePath = "/customers";
    private final String apiDoc = "api/docs/CustomerService.yaml";

    public Response getCustomer(int id, String token) {
        return request(token)
                .when()
                .get(basePath + "/" + id)
                .then()
                .extract().response();
    }

    public Response createCustomer(Object... keyValues) {
        String bodyJson = RequestBodyBuilder.fromYaml(apiDoc,
                "create", OverrideUtils.toMap(keyValues));
        return request(token)
                .body(bodyJson)
                .when()
                .post(basePath)
                .then()
                .extract().response();
    }

    public Response updateCustomer(int id, Object... keyValues) {
        String bodyJson = RequestBodyBuilder.fromYaml(apiDoc,
                "update", OverrideUtils.toMap(keyValues));
        return request(token)
                .body(bodyJson)
                .when()
                .put(basePath + "/" + id)
                .then()
                .extract().response();
    }

    public Response deleteCustomer(int id) {
        return request(token)
                .when()
                .delete(basePath + "/" + id)
                .then()
                .extract().response();
    }
}
