import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SanityTest {

    private String host;

    @BeforeEach
    public void setup() {
        host = System.getProperty("host");
        Assertions.assertNotNull(host, "La propiedad 'host' no se ha especificado. Ejecuta el test con '-Dhost=<HOST>'.");
    }

    @Test
    public void testSanity() throws IOException {
        String bookId = "OL27479W";
        String url = host + "/books/" + bookId;

        int maxRetries = 3;
        int retryDelayMillis = 2000;
        int responseCode = 0;
        String response = "";

        for (int i = 0; i < maxRetries; i++) {
            try {
                URL urlObj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");
                responseCode = connection.getResponseCode();
                response = connection.getResponseMessage();
                if (responseCode == 200) {
                    break;
                }
            } catch (IOException e) {
                // Retry after delay
                try {
                    Thread.sleep(retryDelayMillis);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        Assertions.assertEquals(200, responseCode, "La solicitud no devuelve un código 200 OK");
        Assertions.assertTrue(response.length() <= 953, "La descripción del libro es mayor a 953 caracteres");
    }
}
