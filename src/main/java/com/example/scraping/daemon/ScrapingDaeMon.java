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

@Service
public class ScrapingDaeMon {
    public void run() throws Exception {
        System.out.println("ScrapingDaeMon start ..");

        /* ep.1
        String url = "http://prod.danawa.com/info/?pcode=7837693&cate=10332252";
        Document doc = null;

        doc = Jsoup.connect(url).get();

        //System.out.println(doc);

        Elements element = doc.select("tbody.high_list");

        Iterator<Element> ie1 = element.select("em.prc_t").iterator();

        while (ie1.hasNext()) {
            System.out.println(ie1.next().text());
        }*/

        /* ep.2*/
        String url = "https://auth.danawa.com/login";
        HttpConnection.Response rs = (HttpConnection.Response) Jsoup.connect(url)
                .data("id", "jh432143")
                .data("password", "!wjdgns3540!")
                .method(Connection.Method.POST).execute();

        Document doc = rs.parse();

        //System.out.println(doc);

        Document wishPage = Jsoup.connect("http://www.danawa.com/myPage/wishList.php").cookies(rs.cookies()).get();

        System.out.println(wishPage);

        Elements element = wishPage.select("div#wishProductListArea");

        Iterator<Element> ie1 = element.select("a.link").iterator();

        while (ie1.hasNext()) {
            //System.out.println(ie1.next().text());
        }

        /* ep.3
        String url = "https://auth.danawa.com/login";
        HttpConnection.Response rs = (HttpConnection.Response) Jsoup.connect(url)
                .data("id", "jh432143")
                .data("password", "!wjdgns3540!")
                .method(Connection.Method.POST).execute();

        Document wishPage = Jsoup.connect("http://www.danawa.com/myPage/ajax/getWishProductList.ajax.php")
                .header("Origin", "http://www.danawa.com")
                .header("Referer", "http://www.danawa.com/myPage/wishList.php")
                .cookies(rs.cookies()).get();

        System.out.println(wishPage);

        Elements element = wishPage.select("tbody tr");

        Iterator<Element> ie1 = element.select("a.link").iterator();

        while (ie1.hasNext()) {
            System.out.println(ie1.next().text());
        }
        */

    }
}
