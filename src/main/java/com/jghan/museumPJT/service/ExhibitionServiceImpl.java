package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.mapper.ExhibitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements  ExhibitionService{

    private final ExhibitionMapper exhibitionMapper;

    //전시조회
    @Override
    public String findExhibitionByName(String eName) {
        return exhibitionMapper.selectExhibitionByName(eName);
    }

    //전시저장
    @Override
    public boolean saveExhibition(ExhibitionDTO exhibitionDTO) {

        int queryResult = 0;
        queryResult = exhibitionMapper.insertExhibition(exhibitionDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public int selectFinishedExhibition(String today) {
        return exhibitionMapper.selectFinishedExhibition(today);
    }
}
