//package com.jghan.museumPJT.service;
//
//import com.jghan.museumPJT.dto.ExhibitionDTO;
//import com.jghan.museumPJT.mapper.ExhibitionMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class ExhibitionServiceImpl implements  ExhibitionService{
//
//    private final ExhibitionMapper exhibitionMapper;
//
//    //전시조회
//    @Override
//    public String findExhibitionByName(String eName) {
//        return exhibitionMapper.selectExhibition(eName);
//    }
//
//    //전시저장
//    @Override
//    public boolean saveExhibition(ExhibitionDTO exhibitionDTO) {
//
//        int queryResult = 0;
//        queryResult = exhibitionMapper.insertExhibition(exhibitionDTO);
//
//        return (queryResult == 1) ? true : false;
//    }
//}
