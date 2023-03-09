package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuseumApiServiceImpl implements MuseumApiService {

    @Override
    public List<ExhibitionDTO> getExibitionListLeeum() {
        String URL = "https://www.leeum.org/exhibition/exhibition01.asp";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();
        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "exhibitNL").select("li");
            System.out.println(eList);
            for (int i = 0; i<eList.size(); i++){
                ExhibitionDTO ex = new ExhibitionDTO();

                String eName = eList.get(i).select("span.exName").text();
                String eLink = "https://www.leeum.org/exhibition/"+eList.get(i).select("a").attr("href");
                String eImg =eList.get(i).select("a").select("img").attr("src");
                String eStart = StringUtils.substringBefore(eList.get(i).select("span.exDate").text(), " ~");
                if(eStart == null) eStart = "상설전시";
                String eEnd = StringUtils.substringAfter(eList.get(i).select("span.exDate").text(), "~ ");

                ex.setEMuseum("leeum");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setEImg(eImg);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                exList.add(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }

    @Override
    public List<ExhibitionDTO> getExibitionListNationalMuseum() {
        String URL = "https://www.museum.go.kr/site/main/exhiSpecialTheme/list/current";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "info");
            Elements eListImg = doc.select("div.card.report");


            for (int i = 0; i<eList.size(); i++){
                ExhibitionDTO ex = new ExhibitionDTO();

                String eName = eList.get(i).select("a").select("strong").text();
                String eLink = "https://www.museum.go.kr"+eList.get(i).select("a").attr("href");
                String eStart = StringUtils.substringBefore(
                                eList.get(i).getElementsByClass("info-list special").select("li").get(0).select("p").text(), "~");
                String eEnd = StringUtils.substringAfter(
                                eList.get(i).getElementsByClass("info-list special").select("li").get(0).select("p").text(), "~");
                String eImg= "https://www.museum.go.kr"+eListImg.get(i).select("img").last().attr("src");

                ex.setEMuseum("국립중앙박물관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setELink(eLink);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                ex.setEImg(eImg);
                exList.add(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }



}
