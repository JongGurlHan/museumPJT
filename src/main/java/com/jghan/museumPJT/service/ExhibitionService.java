package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.dto.SearchDTO;

import java.util.List;

public interface ExhibitionService {
	
	//전시 검색
	List<ExhibitionDTO> searchExhibition(String keyword);
	
    //전체 전시 조회
    List<ExhibitionDTO> getExhibitionAll();    
    List<ExhibitionDTO> getExhibitionAll(SearchDTO searchDTO);


    //전시 조회(이름)
    String findExhibitionByName(String eName);

    //전시 저장
    boolean saveExhibition(ExhibitionDTO ex);

    List<ExhibitionDTO> selectFinishedExhibition(String today);

    //전시 디스플레이 -> 0
    void updateEdisplayZero (int eIdx);



}
