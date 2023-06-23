package login;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DataRetriever
{
    private String token;

    public DataRetriever(String token) {
        this.token = token;
    }

    public void retrieveData() {
        try {
            URL url = new URL("http://coreprospects.silicontechnolabs.com/dashboard");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LoginExample
    {

        private String token;

        public LoginExample(String email, String password) {
            try {
                URL url = new URL("http://api-coreprospects.silicontechnolabs.com/api/login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                String input = "{\"Email\":\"" + email + "\",\"password\":\"" + password + "\"}";

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
}
