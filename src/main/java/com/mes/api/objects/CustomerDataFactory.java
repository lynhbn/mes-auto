package com.mes.api.objects;

import com.mes.api.requests.CustomerService;
import com.mes.api.requests.PermissionService;
import io.restassured.response.Response;

public class CustomerDataFactory {

    private final CustomerService customerService = new CustomerService();
    private final PermissionService permissionService = new PermissionService();

    // Tao customer thong thuong
    public Response createBasicCustomer(Object... keyValues) {
        return customerService.createCustomer(keyValues);
    }

    // Tạo customer chỉ có quyền read-only
    public Response createCustomerWithReadOnlyPermission(Object... keyValues) {
        Response customer = createBasicCustomer(keyValues);
        Response res = permissionService.setReadOnlyPermission(customer.jsonPath().get("id"));
        return res;
    }

}
