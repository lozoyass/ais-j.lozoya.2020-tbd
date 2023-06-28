package es.codeurjc.ais.sanity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class SanityTest {

    @Test
    public void testSanity() throws IOException {
        String host = System.getProperty("host");
        Assertions.assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");

        String bookId = "OL27479W";
        String url = host + "/books/" + bookId;
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        String response = connection.getResponseMessage();

        Assertions.assertEquals(200, responseCode, "La solicitud no devuelve un código 200 OK");
        Assertions.assertTrue(response.length() <= 953, "La descripción del libro es mayor a 953 caracteres");
    }
}
