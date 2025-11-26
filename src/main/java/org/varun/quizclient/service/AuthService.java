package org.varun.quizclient.service;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {

    private static String jwtToken;

    private HttpRequest generatePostRequest(String uri, JSONObject body) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/auth/" + uri))
                .header("Content-Type", "Application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();
    }

    private HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String logIn(String login, String password) throws Exception {
        JSONObject body = new JSONObject();
        body.put("login", login);
        body.put("password", password);

        HttpRequest request = generatePostRequest("sign-in", body);

        HttpResponse<String> response = getResponse(request);

        JSONObject json = new JSONObject(response.body());
        String message = json.getString("message");

        if (response.statusCode() == 200) {
            jwtToken = json.getString("data");
            return message;
        }

        if (message.contains(":")) {
            message = message.split(":", 2)[1].trim();
        }

        throw new Exception(message);
    }

    public String signUp(String username, String email, String password) throws Exception {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("email", email);
        body.put("password", password);

        HttpRequest request = generatePostRequest("sign-up", body);
        HttpResponse<String> response = getResponse(request);

        JSONObject json = new JSONObject(response.body());
        String message = json.getString("message");

        if (response.statusCode() == 201) {
            return message;
        }
        if (message.contains(":")) {
            message = message.split(":", 2)[1].trim();
        }
        throw new Exception(message);
    }

    public static String getJwtToken() {
        return jwtToken;
    }
}
