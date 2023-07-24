package com.jghan.museumPJT.service.likes;

import com.jghan.museumPJT.domain.likes.Likes;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesService {

	void likes(int exId, int principalId);

	void unlikes(int exId, int principalId);

	List<Likes> findUserLikeEx(int principalId);

}
