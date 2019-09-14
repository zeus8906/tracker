package hu.home.sbv.controller;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import hu.home.sbv.WebApplication;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void init(){
        RestAssured.port = this.port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testHelloHappyPath() {
        when().get("/api/hello")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body(is("Hello Rest!"));
    }
}