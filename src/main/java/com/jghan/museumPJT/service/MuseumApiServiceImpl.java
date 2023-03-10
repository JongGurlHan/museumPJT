package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MuseumApiServiceImpl implements MuseumApiService {

    //private final ExhibitionService exhibitionService;

    @Override
    public List<ExhibitionDTO> getExibitionListLeeum() {
        String URL = "https://www.leeum.org/exhibition/exhibition01.asp";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();
        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "exhibitNL").select("li");
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

//                String storedEx = exhibitionService.findExhibitionByName(eName);
//                if(storedEx == null || storedEx.equals("")){
//                    exhibitionService.saveExhibition(ex);
//                }
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

    @Override
    public List<ExhibitionDTO> getExibitionListSeoulMuseumOfHistory() {
        String URL = "https://museum.seoul.go.kr/www/board/NR_boardList.do?bbsCd=1002&q_exhSttus=next&sso=ok";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();
        try{
            if(URL.indexOf("https://") >= 0){
                MuseumApiServiceImpl.setSSL();
            }

            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("class", "exhibit_gallery exhibit_gallery01").select("li");
            //System.out.println(eList);
            for (int i = 0; i<eList.size(); i++){
                ExhibitionDTO ex = new ExhibitionDTO();

                String eName = eList.get(i).getElementsByClass("tit").select("strong").text();
                String eImg  = "https://museum.seoul.go.kr"+eList.get(i).getElementsByClass("tmb").select("img").attr("src");
                String eLink = "https://museum.seoul.go.kr"+eList.get(i).select("a").first().attr("href");
                String ePeriod = StringUtils.substringAfter(eList.get(i).select("p.period").text(), "기간");
                String eStart = StringUtils.substringBefore(ePeriod, " ~").trim();
                String eEnd = StringUtils.substringAfter(ePeriod, " ~").trim();

                System.out.println("eName: " +eName);
                System.out.println("eImg: " +eImg);
                System.out.println("eLink: " +eLink);
                System.out.println("eStart: " +eStart);
                System.out.println("eEnd: " +eEnd);

                ex.setEMuseum("서울역사박물관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setEImg(eImg);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                exList.add(ex);

//                String storedEx = exhibitionService.findExhibitionByName(eName);
//                if(storedEx == null || storedEx.equals("")){
//                    exhibitionService.saveExhibition(ex);
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }




    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }




}
