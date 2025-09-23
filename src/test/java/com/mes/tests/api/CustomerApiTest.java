package com.mes.tests.api;
import com.mes.api.requests.CustomerService;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CustomerApiTest {

    @Test
    public void testCreateCustomer() {
        CustomerService customerService = new CustomerService();
        Response response = customerService.createCustomer("name", "Customer A",
                "email", "abc@gmail.com",
                "address.city", "Hanoi"
        );
        assertThat(response.statusCode(), equalTo(200));
    }


}
