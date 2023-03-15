package com.jghan.museumPJT.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.service.ExhibitionService;
import com.jghan.museumPJT.service.MuseumService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ExhibitionService exhibitionService;

    @GetMapping("/")
    public String index(Model model){
    	List<ExhibitionDTO> exList = exhibitionService.getExhibitionAll();
        return "index";
    }

}
