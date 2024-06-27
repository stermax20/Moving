package com.moving.moving_app.crawler.service;

import com.moving.moving_app.crawler.domain.CgvVO;
import com.moving.moving_app.crawler.repository.CgvDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class CrawlingService {
    private final CgvDAO cgvDAO;

    public CrawlingService(CgvDAO cgvDAO) {
        this.cgvDAO = cgvDAO;
    }

    public void sampleCrawling() {
        String cgvUrl = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
        Document doc = null;

        try {
            doc = Jsoup.connect(cgvUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = "strong.title";
        Elements eles1 = doc.select(title);

        String img = ".thumb-image > img";
        Elements eles2 = doc.select(img);

        String category = "div.box-image>a";
        Elements eles3 = doc.select(category);

        Iterator<Element> itr1 = eles1.iterator();
        Iterator<Element> itr2 = eles2.iterator();
        Iterator<Element> itr3 = eles3.iterator();

        while (itr3.hasNext()) {
            try {
                String img2 = itr2.next().attr("src");
                String title2 = itr1.next().text();
                String category2 = itr3.next().attr("href");
                String str = "https://www.cgv.co.kr/movies/detail-view/?" + category2.substring(21);

                String cgvUrl2 = str;
                Document doc2 = Jsoup.connect(cgvUrl2).get();

                String category3 = "div>dl>dt";
                Elements eles4 = doc2.select(category3);
                Iterator<Element> itr4 = eles4.iterator();

                String genre = null;
                while (itr4.hasNext()) {
                    String category4 = itr4.next().text();
                    String a = "장르 : ";
                    int c = category4.indexOf(a);
                    if (c != -1) {
                        genre = category4.substring(5);
                    }
                }

                if (genre == null) {
                    continue;
                }

                String dataString = title2 + img2 + genre;

                String hash = generateHash(dataString);

                Optional<CgvVO> existingData = cgvDAO.findByHash(hash);

                if (!existingData.isPresent()) {
                    CgvVO vo = new CgvVO(title2, img2, genre, 0, hash);
                    cgvDAO.save(vo);
                }

            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
