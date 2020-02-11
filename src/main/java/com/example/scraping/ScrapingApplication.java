package com.example.scraping;

import com.example.scraping.daemon.ScrapingDaeMon;
import com.example.scraping.daemon.ScrapingDaeMon2;
import com.example.scraping.daemon.ScrapingDaeMon3;
import com.example.scraping.daemon.ScrapingDaeMon4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ScrapingApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ScrapingApplication.class, args);

        ScrapingDaeMon scrapingDaeMon = ctx.getBean(ScrapingDaeMon.class);
        ScrapingDaeMon2 scrapingDaeMon2 = ctx.getBean(ScrapingDaeMon2.class);
        ScrapingDaeMon3 scrapingDaeMon3 = ctx.getBean(ScrapingDaeMon3.class);
        ScrapingDaeMon4 scrapingDaeMon4 = ctx.getBean(ScrapingDaeMon4.class);

        try {
            //scrapingDaeMon.run();
            //scrapingDaeMon2.run("jh432143", "!wjdgns3540!");
            //scrapingDaeMon3.run("jh432143", "!wjdgns3540!");
            scrapingDaeMon4.run("jh432143", "!wjdgns3540!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
