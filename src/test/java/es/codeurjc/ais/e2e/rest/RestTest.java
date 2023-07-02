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

    @Test
    public void sanityTest() throws Exception {

        String host = System.getProperty("host");
        org.junit.jupiter.api.Assertions.assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");

        Response response = RestAssured.given().baseUri(host).get("/api/books/OL27479W");

        // Verificamos el código de estado de la respuesta
        response.then().statusCode(200).contentType("application/json");

        // Obtenemos la descripción del libro de la respuesta
        String description = response.jsonPath().getString("description");

        // Verificamos la longitud de la descripción
        org.junit.jupiter.api.Assertions.assertTrue(description.length() <= 953, "La descripción del libro es mayor a 953 caracteres");

    }

    @LocalServerPort
    int port;

    @Test
	public void getAllBooks() throws Exception {
        RestAssured.port = port;
        when()
            .get("/api/books/?topic=drama").
        then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json");
    
    }
    
}

