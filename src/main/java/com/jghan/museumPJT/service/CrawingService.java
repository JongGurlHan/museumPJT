package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;

import java.util.List;

public interface CrawingService {

    //전체 전시 업데이트
    void updateExhibitionAll();
    
    //1. 리움미술관
    List<ExhibitionDTO> getExibitionListLeeum();

    //2. MMCA서울
    List<ExhibitionDTO> getExibitionListNationalMuseum();

    //3. 서울역사박물관
    List<ExhibitionDTO> getExibitionListSeoulMuseumOfHistory();

    //4.MMCA서울
    List<ExhibitionDTO> getExhibitionListMMCASeoul();



}
