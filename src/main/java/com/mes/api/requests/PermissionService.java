package com.mes.api.requests;
import io.restassured.response.Response;

public class PermissionService extends BaseApi {

    private final String basePath = "/permission";
    private final String apiDoc = "api/docs/PermissionService.yaml";

    public Response setReadOnlyPermission(int customerId) {
        return request(token)
                .when()
                .post(basePath + "/" + customerId + "/permissions/readonly")
                .then()
                .extract().response();
    }

}
