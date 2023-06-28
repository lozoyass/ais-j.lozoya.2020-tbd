package es.codeurjc.ais.e2e.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

    @LocalServerPort
    int port;

    private WebDriver driver;

	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
	}

	@AfterEach
	public void teardown() {
		if (this.driver != null) {
            this.driver.quit();
		}
    }
    
    @Test
    @DisplayName("Check that the default topic is fantasy")
	public void checkDefaultTopic() throws Exception {

        String topic = "fantasy";

        this.driver.get("http://localhost:"+this.port+"/");

        String title = driver.findElement(By.tagName("h1")).getText();
        
        assertEquals("Books (topic="+topic+")", title);
    }
    
}
