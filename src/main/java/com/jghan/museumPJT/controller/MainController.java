package com.jghan.museumPJT.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.service.ExhibitionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ExhibitionService exhibitionService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
    	List<ExhibitionDTO> exList = exhibitionService.getExhibitionAll();
        model.addAttribute("exList", exList);

        return "index";
    }
    
    @ResponseBody
    @GetMapping("/api/search")
    public String search(@RequestParam("keyword") String keyword){
    	List<ExhibitionDTO> exList = exhibitionService.searchExhibition(keyword);

        System.out.println(exList);

        return "keyword";
    }


}
