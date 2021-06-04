package com.cryptocli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    private static final String API_URL = "https://api.nomics.com/v1";

    public static void main(String[] args) throws IOException {

        String ticker = "";
        if (args.length > 0) {
            ticker = args[0];
        } else {
            System.out.println("You need input a stock ticker as parameter.");
            System.exit(-1);
        }

        String authKey = System.getenv("NOMICS_API_KEY");

        if (authKey.isEmpty()) {
            System.out.println("You need to define NOMICS_API_KEY environment variable with your nomics.com auth key.");
            System.exit(-1);
        }

        Timer timer = new Timer();
        timer.schedule(new TickerTimer(authKey, ticker), 0, 2000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (true) {
            line = reader.readLine();
            if (line.isEmpty()) {
                System.exit(0);
            }
        }
    }

    public static void sendGetRequest(String authKey, String ticker)
            throws IOException, InterruptedException, ExecutionException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(
                        String.format("%s/currencies/ticker?key=%s&ids=%s&interval=1d", API_URL, authKey, ticker)))
                .header("accept", "application/json").build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.body());

        System.out.println(actualObj.get(0).get("price"));
    }
}
