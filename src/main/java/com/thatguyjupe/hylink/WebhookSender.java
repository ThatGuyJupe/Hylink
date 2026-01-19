package com.thatguyjupe.hylink;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebhookSender {
    private static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    public void send(String username, String message) throws Exception {
        String json = "{\"content\":\"" + escape("[" + username + "] " + message) + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HylinkPlugin.getConfig().getWebhookUrl()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<Void> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 204 && response.statusCode() != 200) {
            throw new RuntimeException("Webhook failed: HTTP " + response.statusCode());
        }
    }


}
