package com.jghan.museumPJT.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.service.CrawingService;
import com.jghan.museumPJT.service.ExhibitionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawingApiController {

    private final CrawingService crawingService;
    private final ExhibitionService exhibitionService;

    //DB- 전체 전시 조회
    @GetMapping("/api/exhibition/all")
    public ResponseEntity<?> getExhibitionAll(){
        List<ExhibitionDTO> exhibitionAll = exhibitionService.getExhibitionAll();
        System.out.println(exhibitionAll);
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exhibitionAll), HttpStatus.OK);
    }

    //전체 전시 크롤링 업데이트
    @GetMapping("/api/display/all")
    public ResponseEntity<?> updateExhibitionAll(){
    	crawingService.updateExhibitionAll();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", null), HttpStatus.OK);
    }

    @GetMapping("/api/display/leeum")
    public ResponseEntity<?> displayLeeum(){
        List<ExhibitionDTO> exList = crawingService.getExibitionListLeeum();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

    @GetMapping("/api/display/nationalMuseum")
    public ResponseEntity<?> displayNationalMuseum(){
        List<ExhibitionDTO> exList = crawingService.getExibitionListNationalMuseum();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

    @GetMapping("/api/display/seoulMuseumOfHistory")
    public ResponseEntity<?> displaySeoulMuseumOfHistory(){
        List<ExhibitionDTO> exList = crawingService.getExibitionListSeoulMuseumOfHistory();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

}
