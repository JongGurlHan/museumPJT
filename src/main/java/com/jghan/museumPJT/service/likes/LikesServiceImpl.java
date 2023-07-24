package com.jghan.museumPJT.service.likes;

import com.jghan.museumPJT.domain.likes.Likes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jghan.museumPJT.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
		likesRepository.unlikes(exId, principalId);
	}

	@Override
	public List<Likes> findUserLikeEx(int principalId) {
		return likesRepository.findUserLikeEx(principalId);
	}

}
