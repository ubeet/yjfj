package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static int ss = 0;
    public static String sd;
    private static List<Float> zxc;

    public List<Float> getZxc(){
        return zxc;
    }

    public static void setZxc(List<Float> zxc){
        Bot.zxc = zxc;
    }


    public static LongPollingBot getBot() {
        return new Bot();
    }

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        if(ss == 0) {
            try {
                sendMsg(update.getMessage().getChatId().toString(), message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(ss == 1){
            try {
                sendMsgCalc(update.getMessage().getChatId().toString(), message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sendMsgCalc(String chatId, String s) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        try{
            float d = Float.parseFloat(s);
            String text;
            if (sd.equals("USD to UAH")){
                text = d + " доллларов в гривны: " + d* getZxc().get(0);
                sendMessage.setText(text);
                ss = 0;
            }if (sd.equals("UAH to USD")){
                text = d + " гривен в доллары: " + d/getZxc().get(1);
                sendMessage.setText(text);
                ss = 0;
            }if (sd.equals("EUR to UAH")){
                text = d + " евро в гривны: " + d*getZxc().get(2);
                sendMessage.setText(text);
                ss = 0;
            }if (sd.equals("UAH to EUR")){
                text = d + " гривен в евро: " + d/ getZxc().get(3);
                sendMessage.setText(text);
                ss = 0;
            }
        }catch (NumberFormatException e) {
            System.out.println();
        }
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println();
        }

    }

    public synchronized void sendMsg(String chatId, String s) throws IOException { // https://api.monobank.ua/bank/currency
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sd = s;
        if (s.equals("USD to UAH")){
            sendMessage.setText("Введите сумму:");
            ss = 1;
        }else if(s.equals("UAH to USD")){
            sendMessage.setText("Введите сумму:");
            ss = 1;
        }else if (s.equals("EUR to UAH")){
            sendMessage.setText("Введите сумму:");
            ss = 1;
        }else if(s.equals("UAH to EUR")){
            sendMessage.setText("Введите сумму:");
            ss = 1;
        }else{
            sendMessage.setText("Выберите способ обмена");
        }

        setButtons(sendMessage);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println();
        }
    }

    public synchronized void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("USD to UAH"));
        keyboardFirstRow.add(new KeyboardButton("UAH to USD"));
        keyboard.add(keyboardFirstRow);
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("EUR to UAH"));
        keyboardSecondRow.add(new KeyboardButton("UAH to EUR"));
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public String getBotUsername() {
        return "ConvertBot";
    }

    @Override
    public String getBotToken() {
        return "2033573053:AAFlI-5Tod9Op2AhKG5Cg_0RaUFO0fKF84c";
    }
}