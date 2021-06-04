package com.cryptocli;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

class TickerTimer extends TimerTask {
    private String authKey;
    private String ticker;

    public TickerTimer(String authKey, String ticker) {
        this.authKey = authKey;
        this.ticker = ticker;
    }

    public void run() {
        try {
            App.sendGetRequest(this.authKey, this.ticker);
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println("Failed to get ticker data.");

            e.printStackTrace();
        }
    }
}
