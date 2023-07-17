package com.jghan.museumPJT.service.likes;

public interface LikesService {

	void likes(int exId, int principalId);

	void unlikes(int exId, int principalId);

}
