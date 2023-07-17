package com.jghan.museumPJT.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Integer>{


	@Modifying
	@Query(value = "INSERT INTO likes(exhibitionId, userId, createDate) VALUES (:exhibitionId, :principalId, now())", nativeQuery = true)
	void likes(@Param("exhibitionId") int exhibitionId, @Param("principalId") int principalId);


//	@Modifying
//	@Query(value = "DELETE FROM likes WHERE exhibitionId =:exhibitionId AND userId =:principalId", nativeQuery = true)
//	void unlikes(@Param("exhibitionId") int exhibitionId, @Param("principalId") int principalId);
}
