package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.List;

public class Main extends Bot {


    public static void main(String[] args) throws IOException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Runnable task = new Runnable() {

            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        asd();
                        Thread.sleep(300000);
                    }catch (InterruptedException | IOException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        Thread ss = new Thread(task);
        ss.start();
        try {
            telegramBotsApi.registerBot(Bot.getBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
    public static void asd() throws IOException {
        List<Float> zxc = new Parse().getPage();
        Bot.setZxc(zxc);

    }
}
