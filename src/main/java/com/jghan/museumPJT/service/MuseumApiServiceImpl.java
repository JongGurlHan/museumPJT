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
           /* if(URL.indexOf("https://") >= 0){
                MuseumApiServiceImpl.setSSL();
            }*/



            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "exhibitNL").select("li");
            System.out.println(eList);
            for (int i = 0; i<eList.size(); i++){

               // System.out.println(eList.get(i));
                ExhibitionDTO ex = new ExhibitionDTO();

               // System.out.println("ex:"+ex);

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
                //System.out.println(ex);
                exList.add(ex);


            }
            System.out.println("exList:"+exList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }
//https://colabear754.tistory.com/87
    @Override
    public List<ExhibitionDTO> getExibitionListNationalMuseum() {
        System.out.println("test");
        String URL = "https://www.museum.go.kr/site/main/exhiSpecialTheme/list/current";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "show-list report special").select("li");
            System.out.println(eList);

            for (int i = 0; i<eList.size(); i++){

                System.out.println(eList.get(i));
                ExhibitionDTO ex = new ExhibitionDTO();

                // System.out.println("ex:"+ex);

//                String eName = eList.get(i).select("span.exName").text();
//                String eLink = "https://www.leeum.org/exhibition/"+eList.get(i).select("a").attr("href");
//                String eImg =eList.get(i).select("a").select("img").attr("src");
//                String eStart = StringUtils.substringBefore(eList.get(i).select("span.exDate").text(), " ~");
//                if(eStart == null) eStart = "상설전시";
//                String eEnd = StringUtils.substringAfter(eList.get(i).select("span.exDate").text(), "~ ");
//
//                ex.setEMuseum("leeum");
//                ex.setEName(eName);
//                ex.setELink(eLink);
//                ex.setEImg(eImg);
//                ex.setEStart(eStart);
//                ex.setEEnd(eEnd);
//                //System.out.println(ex);
//                exList.add(ex);


            }
            System.out.println("exList:"+exList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
