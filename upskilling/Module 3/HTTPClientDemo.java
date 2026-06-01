import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
public class HTTPClientDemo {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/octocat"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        System.out.println("Sending GET request to GitHub API...");
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred during the HTTP request: " + e.getMessage());
        }
    }
}
