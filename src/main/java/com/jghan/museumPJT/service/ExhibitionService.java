package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;

import java.util.List;

public interface ExhibitionService {
    //전시 조회(이름)
    public String findExhibitionByName(String eName);

    //전시 저장
    public boolean saveExhibition(ExhibitionDTO ex);


}
