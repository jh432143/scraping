package com.example.scraping.daemon;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapingDaeMon2 {
    private String id;
    private String pw;

    private WebClient webClient;
    private HtmlPage htmlPage;

    public void run(String id, String pw) throws Exception {
        this.id = id;
        this.pw = pw;

        // 로그인페이지접속
        Connection.Response loginPageResponse = Jsoup.connect("http://auth.danawa.com/login")
                .timeout(5000)
                .header("Origin", "http://www.danawa.com")
                .header("Referer", "http://www.danawa.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.7,en;q=0.6")
                .method(Connection.Method.GET)
                .execute();

        Map<String, String> loginTryCookie = loginPageResponse.cookies();

        Document loginPageDocument = loginPageResponse.parse();
        //System.out.println("loginPageDocument :: "+loginPageDocument);

        // 로그인시도
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";

        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("password", pw);
        data.put("loginMemberType", "general");
        data.put("redirectUrl", "http://www.danawa.com/");


        Connection.Response response = Jsoup.connect("http://auth.danawa.com/login")
                .userAgent(userAgent)
                .timeout(3000)
                .header("Origin", "https://auth.danawa.com")
                .header("Referer", "https://auth.danawa.com/login?url=http%3A%2F%2Fwww.danawa.com%2F")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.7,en;q=0.6")
                .cookies(loginTryCookie)
                .data(data)
                .method(Connection.Method.POST)
                .execute();

        Map<String, String> loginCookie = response.cookies();

        Document loginDocument = response.parse();
        System.out.println("loginDocument :: "+loginDocument);

        // 페이지 가져오기
//        Document doc = Jsoup.connect("http://www.danawa.com/myPage/wishList.php") //http://www.danawa.com/myPage/ajax/getWishProductList.ajax.php
//                .userAgent(userAgent)
//                .header("Referer", "http://www.danawa.com/myPage/wishList.php")
//                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                .header("Content-Type", "application/json;charset=UTF-8")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.7,en;q=0.6")
//                .cookies(loginCookie) // 위에서 얻은 '로그인 된' 쿠키
//                .get();
//
//        System.out.println("doc :: "+doc);
    }
}
