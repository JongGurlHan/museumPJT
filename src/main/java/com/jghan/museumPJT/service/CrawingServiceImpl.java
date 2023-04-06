package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CrawingServiceImpl implements CrawingService {

    private final ExhibitionService exhibitionService;

    @Override
    public void updateExhibitionAll() {
        getExibitionListLeeum();
        getExibitionListNationalMuseum();
        getExibitionListSeoulMuseumOfHistory();
        getExhibitionListSoma();
    }

    //====1. 리움미술관====
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
                if(eStart == null) eStart = null;
                String eEnd = StringUtils.substringAfter(eList.get(i).select("span.exDate").text(), "~ ");
	            String eAddress = "서울특별시 용산구 이태원로55길 60-16";

                ex.setEMuseum("리움미술관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setEImg(eImg);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                ex.setEDisplay(1);
                ex.setEAddress(eAddress);
                ex.setELat("37.53836720511353");
                ex.setELong("126.99927585574612");
                exList.add(ex);

                String storedEx = exhibitionService.findExhibitionByName(eName); 
                if(!eName.equals(storedEx)) {                //1. 크롤링한 제목이 db에 없다면 해당 전시 저장
                    exhibitionService.saveExhibition(ex);
                }
            }
            // 2.db상에서 e_end값이 오늘보다 지났다면 eDisplay를 1 -> 0  수정
            String today = LocalDate.now().toString(); //오늘날짜 구한다
            List<ExhibitionDTO> finishedEx = exhibitionService.selectFinishedExhibition(today); //오늘날짜보다 과거 날짜의 전시 찾는다
            for (int i = 0; i<finishedEx.size(); i++){
                exhibitionService.updateEdisplayZero(finishedEx.get(i).getEIdx());
            }


            } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }

    //====2. 국립중앙박물관====
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
	            String eAddress = "서울특별시 용산구 서빙고로 137";

                ex.setEMuseum("국립중앙박물관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setELink(eLink);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                ex.setEImg(eImg);
                ex.setEAddress(eAddress);
                ex.setELat("37.52383779828461");
                ex.setELong("126.98018039613258");
                exList.add(ex);

                String storedEx = exhibitionService.findExhibitionByName(eName);
                if(!eName.equals(storedEx)) {                //1. 크롤링한 제목이 db에 없다면 해당 전시 저장
                    exhibitionService.saveExhibition(ex);
                }
            }
            String today = LocalDate.now().toString(); //오늘날짜 구한다
            List<ExhibitionDTO> finishedEx = exhibitionService.selectFinishedExhibition(today); //오늘날짜보다 과거 날짜의 전시 찾는다
            for (int i = 0; i<finishedEx.size(); i++){
                exhibitionService.updateEdisplayZero(finishedEx.get(i).getEIdx());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }

    //3. 서울역사 박물관
    @Override
    public List<ExhibitionDTO> getExibitionListSeoulMuseumOfHistory() {
        String URL = "https://museum.seoul.go.kr/www/board/NR_boardList.do?bbsCd=1002&q_exhSttus=next&sso=ok";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();
        try{
            if(URL.indexOf("https://") >= 0){
            	CrawingServiceImpl.setSSL();
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
	            String eAddress = "서울특별시 종로구 새문안로 55";
	                
                ex.setEMuseum("서울역사박물관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setEImg(eImg);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                ex.setEAddress(eAddress);
                ex.setELat("37.570496718719575");
                ex.setELong("126.97054636821485");
                exList.add(ex);

                String storedEx = exhibitionService.findExhibitionByName(eName);
                if(!eName.equals(storedEx)) {                //1. 크롤링한 제목이 db에 없다면 해당 전시 저장
                    exhibitionService.saveExhibition(ex);
                }
            }
            String today = LocalDate.now().toString(); //오늘날짜 구한다
            List<ExhibitionDTO> finishedEx = exhibitionService.selectFinishedExhibition(today); //오늘날짜보다 과거 날짜의 전시 찾는다
            for (int i = 0; i<finishedEx.size(); i++){
                exhibitionService.updateEdisplayZero(finishedEx.get(i).getEIdx());
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

    //4. 소마미술관
    @Override
    public List<ExhibitionDTO> getExhibitionListSoma() {
        String URL = "https://soma.kspo.or.kr/main#p2";
        Document doc;
        List<ExhibitionDTO> exList = new ArrayList<>();

        try{
            doc = Jsoup.connect(URL).get();            

            Elements eList = doc.select("div#s2_slide").select("div.s2_slide_box.item");
            
            
            for (Element item : eList){
                ExhibitionDTO ex = new ExhibitionDTO();
                
                String eName = item.getElementsByClass("txt_bottom").text();
	            String eLink = "https://soma.kspo.or.kr"+item.select("a").first().attr("href");
                String eImg  = "https://soma.kspo.or.kr"+item.getElementsByClass("s2_slide_box_inner").select("img").attr("src");
                String ePeriod = item.getElementsByClass("txt_right").text();
	            String eStart = StringUtils.substringBefore(ePeriod, " ~ ").trim().replace(".", "-");
	            String eEnd = StringUtils.substringAfter(ePeriod, " ~ ").trim().replace(".", "-");
	            String eAddress = "서울 송파구 위례성대로 51";
	            
	            ex.setEMuseum("소마미술관");
                ex.setEName(eName);
                ex.setELink(eLink);
                ex.setEImg(eImg);
                ex.setEStart(eStart);
                ex.setEEnd(eEnd);
                ex.setEDisplay(1);
                ex.setEAddress(eAddress);
                ex.setELat("37.5168788336674");
                ex.setELong("127.11800163775546");
                exList.add(ex);

	            System.out.println("eName: "+ eName);
                System.out.println("eImg: "+ eImg);
                System.out.println("ePeriod: "+ ePeriod);
                System.out.println("eStart: "+ eStart);
                System.out.println("eEnd: "+ eEnd);
                System.out.println("eLink: "+ eLink);
                
                String storedEx = exhibitionService.findExhibitionByName(eName); 
                if(!eName.equals(storedEx)) {                //1. 크롤링한 제목이 db에 없다면 해당 전시 저장
                    exhibitionService.saveExhibition(ex);
                }
            }
            String today = LocalDate.now().toString(); //오늘날짜 구한다
            List<ExhibitionDTO> finishedEx = exhibitionService.selectFinishedExhibition(today); //오늘날짜보다 과거 날짜의 전시 찾는다
            for (int i = 0; i<finishedEx.size(); i++){
                exhibitionService.updateEdisplayZero(finishedEx.get(i).getEIdx());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exList;
    }

    //5. 
















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
