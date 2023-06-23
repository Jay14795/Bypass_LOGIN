package login;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LoginExample {

    private String token;

    public LoginExample(String email, String password) {
        try {
            URL url = new URL("http://api-coreprospects.silicontechnolabs.com/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

            conn.setDoOutput(true);
            conn.getOutputStream().write(input.getBytes());

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                String response = scanner.nextLine();
                if (response.contains("token")) {
                    String[] parts = response.split(":");
                    token = parts[1].replaceAll("\"", "").trim();
                    break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }
}
