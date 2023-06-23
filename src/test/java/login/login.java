package login;

import org.testng.annotations.Test;

public class login
{

    @Test
    public void Login() {
        String email = "superadmin@coreprospect.com";
        String password = "Core@123";

        // Login and get token
        LoginExample loginExample = new LoginExample(email, password);
        String token = loginExample.getToken();

        // Retrieve data using the token
        DataRetriever dataRetriever = new DataRetriever(token);
        dataRetriever.retrieveData();
    }
}
