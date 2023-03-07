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

    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    // SSL 우회 등록
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


    @Override
    public List<ExhibitionDTO> getExibitionListLeeum() {
        String URL = "https://www.leeum.org/exhibition/exhibition01.asp";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            if(URL.indexOf("https://") >= 0){
                MuseumApiServiceImpl.setSSL();
            }



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


            }
            System.out.println("exList:"+exList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }
//https://colabear754.tistory.com/87
    @Override
    public List<ExhibitionDTO> getExibitionListMmcaSeoul() {
        String URL = "http://www.mmca.go.kr/visitingInfo/seoulInfo.do";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            doc = Jsoup.connect(URL).get();
            Elements eList = doc.getElementsByAttributeValue("id", "exhList").select("li");
            System.out.println(eList);

            for (int i = 0; i<eList.size(); i++){

                // System.out.println(eList.get(i));
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
