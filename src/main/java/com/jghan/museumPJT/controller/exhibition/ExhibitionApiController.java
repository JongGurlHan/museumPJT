package com.jghan.museumPJT.controller.exhibition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.service.likes.LikesService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ExhibitionApiController {
	
    private final LikesService likesService;

	//전시 좋아요
	@PostMapping("/api/ex/{exId}/likes")
	public ResponseEntity<?>likes(@PathVariable int exId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		likesService.elike(exId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요성공", null), HttpStatus.CREATED);
	}

}
