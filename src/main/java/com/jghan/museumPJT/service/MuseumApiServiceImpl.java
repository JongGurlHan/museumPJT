package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuseumApiServiceImpl implements MuseumApiService {

    @Override
    public List<ExhibitionDTO> getExibitionList() {
        String URL = "https://www.leeum.org/exhibition/exhibition01.asp";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "exhibitNL").select("li");

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

//                System.out.println("이름:" + eName); //이름
//                System.out.println("링크: "+ eLink);
//                System.out.println("이미지: "+ eImg);
//                System.out.println("시작일:" + eStart); //시작일
//                System.out.println("마감일:" + eEnd); //시작일
//                System.out.println("=====================");

            }
            System.out.println("exList:"+exList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }
}
