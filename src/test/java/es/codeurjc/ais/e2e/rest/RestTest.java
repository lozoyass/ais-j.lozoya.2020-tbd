package es.codeurjc.ais.e2e.rest;

import static org.awaitility.Awaitility.await;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@DisplayName("REST tests")
public class RestTest {

    @Test
	public void getAllBooks() {
        String host = System.getProperty("host");
        assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");

        await().atMost(Duration.ofSeconds(60)).until(() -> {
            Response response = RestAssured.given().baseUri(host).get("/api/books/?topic=drama");
            return response.statusCode() == 200 && response.contentType().equals("application/json");
        });
    }

    @Test
    public void sanityTest() {
        String host = System.getProperty("host");
        assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");

        await().atMost(Duration.ofSeconds(60)).until(() -> {
            Response response = RestAssured.given().baseUri(host).get("/api/books/OL27479W");

            // Verificar el código de estado de la respuesta
            if (response.statusCode() != 200 || !response.contentType().equals("application/json")) {
                return false;
            }

            // Obtener la descripción del libro de la respuesta
            String description = response.jsonPath().getString("description");

            // Verificar la longitud de la descripción
            return description.length() <= 953;
        });
    }
}
