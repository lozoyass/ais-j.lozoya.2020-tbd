package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("REST tests")
public class RestTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
	public void getAllBooks() throws Exception {

        when()
            .get("/api/books/?topic=drama").
        then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json");
    
    }

    @Test
    public void sanityTest() throws Exception {

        String host = System.getProperty("host");
        org.junit.jupiter.api.Assertions.assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");

        Response response = RestAssured.given().baseUri(host).get("/api/books/OL27479W");


        for (int i = 0; i < maxRetries; i++) {
            try {
                Thread.sleep(retryDelayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response = RestAssured.given().baseUri(host).get("/api/books/OL27479W");
            if (response.getStatusCode() == 200) {
                break;
            }
        }
        // Verificamos el código de estado de la respuesta
        response.then().statusCode(200).contentType("application/json");

        // Obtenemos la descripción del libro de la respuesta
        description = response.jsonPath().getString("description");

        // Verificamos la longitud de la descripción
        org.junit.jupiter.api.Assertions.assertTrue(description.length() <= 953, "La descripción del libro es mayor a 953 caracteres");

    }
    
}