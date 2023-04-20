package com.jghan.museumPJT.service.likes;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jghan.museumPJT.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesServiceImpl implements LikesService{
	
	private final LikesRepository likesRepository;

	@Transactional
	@Override
	public void elike(int exId, int principalId) {
		likesRepository.eLikes(exId, principalId);		
	}

}
