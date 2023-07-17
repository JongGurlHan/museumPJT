package com.jghan.museumPJT.controller.exhibition;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ExhibitionApiController {
	
    private final LikesService likesService;

	//전시 like
	@PostMapping("/api/ex/{exId}/likes")
	public ResponseEntity<?> likes(@PathVariable int exId, @AuthenticationPrincipal PrincipalDetails principalDetails){

		likesService.likes(exId, principalDetails.getUser().getId());

		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요성공", null), HttpStatus.CREATED);

	}

	//전시  좋아요 취소
	@DeleteMapping("/api/ex/{exId}/likes")
	public ResponseEntity<?>unLikes(@PathVariable int exId, @AuthenticationPrincipal PrincipalDetails principalDetails){

		likesService.unlikes(exId, principalDetails.getUser().getId());

		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 취소성공", null), HttpStatus.OK);

	}

}
