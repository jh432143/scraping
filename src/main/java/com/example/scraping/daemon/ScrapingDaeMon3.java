package com.example.scraping.daemon;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapingDaeMon3 {
    private String id;
    private String pw;

    public void run(String id, String pw) throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("password", pw);

        String url = "https://auth.danawa.com/login";
        HttpConnection.Response rs = (HttpConnection.Response) Jsoup.connect(url)
                .data(data)
                .method(Connection.Method.POST).execute();

        Document doc = rs.parse();

        Map<String, String> loginCookie = rs.cookies();

        //Document wishPage = Jsoup.connect("http://www.danawa.com/myPage/wishList.php").cookies(loginCookie).get();
        //System.out.println(wishPage);


        HttpConnection.Response response = (HttpConnection.Response) Jsoup.connect("http://www.danawa.com/myPage/wishList.php")
                .cookies(loginCookie)
                .method(Connection.Method.GET)
                .execute();

        HttpConnection.Response response2 = (HttpConnection.Response) Jsoup.connect("http://www.danawa.com/myPage/ajax/getWishProductList.ajax.php")
                .cookies(loginCookie)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute();

        Document wishDoc = response2.parse();
        System.out.println(wishDoc);



    }
}
