package org.varun.quizclient.service;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {

    private static String jwtToken;

    public String login(String login, String password) throws Exception {
        HttpClient client=HttpClient.newHttpClient();

        JSONObject body = new JSONObject();
        body.put("login", login);
        body.put("password", password);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/auth/sign-in"))
                .header("Content-Type","Application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());

        JSONObject json=new JSONObject(response.body());
        String message=json.getString("message");

        if(response.statusCode()==200){
            jwtToken=json.getString("data");
            return message;
        }

        if(message.contains(":")){
            message=message.split(":",2)[1].trim();
        }

        throw new Exception(message);
    }

    public static String getJwtToken(){
        return jwtToken;
    }
}
