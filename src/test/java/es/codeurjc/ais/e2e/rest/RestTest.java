package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

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

        Response response = RestAssured.get("/api/books/OL27479W");

        // Verificar el código de estado de la respuesta
        response.then().statusCode(200).contentType("application/json");

        // Obtener la descripción del libro de la respuesta
        String description = response.jsonPath().getString("description");

        // Verificar la longitud de la descripción
        org.junit.jupiter.api.Assertions.assertTrue(description.length() <= 953, "La descripción del libro es mayor a 953 caracteres");


        /*
        when().get("/api/books/OL27479W").then().assertThat().statusCode(200).contentType("application/json");

        String host = System.getProperty("host");
        org.junit.jupiter.api.Assertions.assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");
        
        String bookId = "OL27479W";
        String url = host + "api/books/OL27479W";

        int maxRetries = 3;
        int retryDelayMillis = 2000;
        int responseCode = 0;
        String response = "";

        for (int i = 0; i < maxRetries; i++) {
            try {
                java.net.URL urlObj = new java.net.URL(url);
                java.net.HttpURLConnection connection = (java.net.HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");
                responseCode = connection.getResponseCode();
                response = connection.getResponseMessage();
                if (responseCode == 200) {
                    break;
                }
            } catch (java.io.IOException e) {
                // Retry after delay

                try {
                    Thread.sleep(retryDelayMillis);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        org.junit.jupiter.api.Assertions.assertEquals(200, responseCode, "La solicitud no devuelve un código 200 OK");
        org.junit.jupiter.api.Assertions.assertTrue(response.length() <= 953, "La descripción del libro es mayor a 953 caracteres");
        */

    }
    
}