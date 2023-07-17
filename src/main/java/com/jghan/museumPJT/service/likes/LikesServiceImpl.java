package com.jghan.museumPJT.service.likes;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jghan.museumPJT.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesServiceImpl implements LikesService{
	
	private final LikesRepository likesRepository;


	@Override
	@Transactional
	public void likes(int exId, int principalId) {
		likesRepository.likes(exId, principalId);
	}

	@Override
	@Transactional
	public void unlikes(int exId, int principalId) {
		//likesRepository.unlikes(exId, principalId);

	}

}
