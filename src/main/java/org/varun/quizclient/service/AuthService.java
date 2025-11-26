package org.varun.quizclient.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthService {

    private static String jwtToken;

    public String login(String login, String password) throws Exception {
        URL url = new URL("http://localhost:8080/api/auth/sign-in");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject body = new JSONObject();
        body.put("login", login);
        body.put("password", password);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        JSONObject json=new JSONObject(response.toString());

        String message=json.getString("message");

        if(conn.getResponseCode()==200){
            jwtToken=json.getString("data");
            return message;
        }

        throw new Exception(message);
    }

    public static String getJwtToken(){
        return jwtToken;
    }
}
