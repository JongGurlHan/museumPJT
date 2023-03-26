package com.jghan.museumPJT.controller;

import java.util.List;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
    	List<ExhibitionDTO> exList = exhibitionService.getExhibitionAll();
        model.addAttribute("exList", exList);
        //model.addAttribute("principal", principalDetails.getUser() );

        return "index";
    }

}
