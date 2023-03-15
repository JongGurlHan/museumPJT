package com.jghan.museumPJT.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.service.ExhibitionService;
import com.jghan.museumPJT.service.MuseumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MusiumApiController {

    private final MuseumService museumService;
    private final ExhibitionService exhibitionService;

    @GetMapping("/api/exhibition/all")
    public ResponseEntity<?> getExhibitionAll(){
        List<ExhibitionDTO> exhibitionAll = exhibitionService.getExhibitionAll();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exhibitionAll), HttpStatus.OK);
    }



    @GetMapping("/api/display/all")
    public ResponseEntity<?> updateExhibitionAll(){
        museumService.updateExhibitionAll();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", null), HttpStatus.OK);
    }

    @GetMapping("/api/display/leeum")
    public ResponseEntity<?> displayLeeum(){
        List<ExhibitionDTO> exList = museumService.getExibitionListLeeum();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

    @GetMapping("/api/display/nationalMuseum")
    public ResponseEntity<?> displayNationalMuseum(){
        List<ExhibitionDTO> exList = museumService.getExibitionListNationalMuseum();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

    @GetMapping("/api/display/seoulMuseumOfHistory")
    public ResponseEntity<?> displaySeoulMuseumOfHistory(){
        List<ExhibitionDTO> exList = museumService.getExibitionListSeoulMuseumOfHistory();
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);
    }

}
