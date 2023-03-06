package com.jghan.museumPJT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@SpringBootApplication
public class museumPJTApplication {

	public static void main(String[] args) {
		SpringApplication.run(museumPJTApplication.class, args);

//		String URL = "https://www.leeum.org/exhibition/exhibition01.asp";
//		Document doc;
//
//		try{
//			doc = Jsoup.connect(URL).get();
//			Elements eList = doc.getElementsByAttributeValue("class", "exhibitNL").select("li");
//
//			for (int i = 0; i<eList.size(); i++){
//
//				System.out.println(eList.get(i));
//
//				String eName = eList.get(i).select("span.exName").text();
//				String eLink = "https://www.leeum.org/exhibition/"+eList.get(i).select("a").attr("href");
//				String eImg =eList.get(i).select("a").select("img").attr("src");
//				String eStart = StringUtils.substringBefore(eList.get(i).select("span.exDate").text(), " ~");
//					if(eStart == null) eStart = "상설전시";
//				String eEnd = StringUtils.substringAfter(eList.get(i).select("span.exDate").text(), "~ ");
//
//				System.out.println("이름:" + eName); //이름
//				System.out.println("링크: "+ eLink);
//				System.out.println("이미지: "+ eImg);
//				System.out.println("시작일:" + eStart); //시작일
//				System.out.println("마감일:" + eEnd); //시작일
//				System.out.println("=====================");
//
//			}
//
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

	}

}
