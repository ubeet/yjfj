package com.company;

import java.io.IOException;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parse {
    public synchronized List<Float> getPage() throws IOException {
        String url = "https://api.monobank.ua/bank/currency";
        Document connection = Jsoup.connect(url)
                .userAgent("Mozilla")
                .timeout(52000)
                .cookie("cookiename", "val234")
                .referrer("http://google.com")
                .header("headersecurity", "xyz123")
                .ignoreContentType(true).get();
        String jsonArray = connection.text();
        List<Staff> staff1 = JSON.parseArray(jsonArray, Staff.class);
        List<Float> sf = new ArrayList<Float>();

        sf.add(staff1.get(0).getRateBuy());
        sf.add(staff1.get(0).getRateSell());
        sf.add(staff1.get(1).getRateBuy());
        sf.add(staff1.get(1).getRateSell());
        return sf;
    }
}
