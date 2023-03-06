package com.jghan.museumPJT.controller;

import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.service.MuseumApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusiumApiController {

    private final MuseumApiService museumApiService;

    @GetMapping("/api/display/leeum")
    public ResponseEntity<?> displayLeeum(){

        List<ExhibitionDTO> exList = museumApiService.getExibitionList();

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", exList), HttpStatus.OK);

    }

}
