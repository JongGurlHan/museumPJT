package com.jghan.museumPJT.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.dto.SearchDTO;
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

        if(principalDetails != null){
            model.addAttribute("loginUser", principalDetails.getUser());

            String userName = principalDetails.getUser().getUsername();

            model.addAttribute("userName", userName);
            System.out.println("loginUser = " + principalDetails.getUser());

            System.out.println("loginUser2 = " + principalDetails.getUser().getUsername());
        }
        return "index";
    }

    @ResponseBody
    @GetMapping("/api/exhibition")
    public ResponseEntity<?> exhibition(SearchDTO searchDTO, @AuthenticationPrincipal PrincipalDetails principalDetails){

        System.out.println("+++전시조회+++");
        System.out.println("searchDTO.getPageNum() = " + searchDTO.getPageNum());
        System.out.println("searchDTO.getPageSize() = " + searchDTO.getPageSize());

        if(principalDetails == null){
            return new ResponseEntity<>(new CMRespDto<>(1, "성공", PageInfo.of(exhibitionService.getExhibitionAll(searchDTO))), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new CMRespDto<>(1, "성공", PageInfo.of(exhibitionService.getExhibitionAll(searchDTO, principalDetails.getUser().getId()))), HttpStatus.OK);
        }



    }
      
    
    
    @ResponseBody
    @GetMapping("/api/search")
    public List<ExhibitionDTO> search(@RequestParam("keyword") String keyword){
    	List<ExhibitionDTO> exList = exhibitionService.searchExhibition(keyword);

        System.out.println(exList);

        return exList;
    }
    
    


}
