package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;

import java.util.List;

public interface MuseumApiService {
    
    //리움미술관
    public List<ExhibitionDTO> getExibitionListLeeum();

    //MMCA서울
    List<ExhibitionDTO> getExibitionListNationalMuseum();

    //서울역사박물관
    List<ExhibitionDTO> getExibitionListSeoulMuseumOfHistory();
}
