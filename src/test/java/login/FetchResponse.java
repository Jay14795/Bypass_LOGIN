package login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.*;
public class FetchResponse
{

        public static void main(String[] args) {

            // Set the path to the ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            // Create a new instance of the DevTools
            DevTools devTools = ((ChromeDriver) driver).getDevTools();

            // Start the DevTools session
            devTools.createSession();

            // Enable the Network domain
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Add a listener to intercept the response
            devTools.addListener(Network.responseReceived(), responseReceived -> {
                Response response = responseReceived.getResponse();

                // Check if the response is for the URL we want
                if (response.getUrl().equals("https://www.example.com")) {

                    // Get the response body
                    devTools.send(Network.getResponseBody(response.getRequestId()), responseBody -> {
                        String body = responseBody.getBody();

                        // Print the response body
                        System.out.println(body);
                    });
                }
            });

            // Navigate to a URL
            driver.get("https://www.example.com");

            // Quit the driver
            driver.quit();
        }
    }

}
