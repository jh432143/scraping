package com.example.scraping.daemon;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class ScrapingDaeMon4 {
    private String id;
    private String pw;

    /**
     * 최종본 ...
     * 결국 호출 시 .data("fSeq", "25961380") 부분이 중요했던 ...
     */
    public void run(String id, String pw) throws Exception {
        /*Document doc = Jsoup.connect("http://search.danawa.com/ajax/getProductList.ajax.php")
                .header("Origin", "http://search.danawa.com")
                .header("Referer", "http://search.danawa.com/")
                .data("query", "잘만").post();

        Elements items = doc.select("li.prod_item");

        for (Element item : items) {
            System.out.println(item.html());
            System.out.println(" ");
        }*/

        this.id = id;
        this.pw = pw;

        // 로그인 계정정보 세팅
        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("password", pw);

        // 로그인시도
        String url = "https://auth.danawa.com/login";
        HttpConnection.Response rs = (HttpConnection.Response) Jsoup.connect(url)
                .data(data)
                .method(Connection.Method.POST).execute();

        // 쿠키저장
        Map<String, String> loginCookie = rs.cookies();

        // 관심리스트 ajax call
        Document doc = Jsoup.connect("http://www.danawa.com/myPage/ajax/getWishProductList.ajax.php")
                .header("Origin", "http://www.danawa.com")
                .header("Referer", "http://www.danawa.com/")
                .cookies(loginCookie)
                .data("fSeq", "25961380").post();

        //System.out.println(doc);

        // 관심상품목록 추출
        Elements contents = doc.select("div.tit");
        Iterator<Element> datas = contents.select("a.link").iterator();
        while (datas.hasNext()) {
            System.out.println(">>> "+datas.next().text());
        }
    }
}
