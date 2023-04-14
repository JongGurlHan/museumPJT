package com.jghan.museumPJT.domain.exhibition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.dto.ExhibitionDTO;

public interface ExhibitionRepository extends JpaRepository<User, Integer>{

	@Query(value = "SELECT\r\n" + 
						"e_name\r\n" + 
					"FROM\r\n" + 
						"exhibition\r\n" + 
					"WHERE\r\n" + 
						"e_name = :eName", nativeQuery = true)
	String findExhibitionByName(@Param("eName") String eName);

	@Query(value = "SELECT * FROM exhibition WHERE e_end < :today ", nativeQuery = true)
	List<ExhibitionDTO> findFinishedExhibition(@Param("today") String today);

	@Modifying
	@Query(value = "UPDATE exhibition SET e_display = 0 WHERE e_idx = :eIdx", nativeQuery = true)
	void updateEdisplayZero(@Param("eIdx") int eIdx);
	
	@Modifying
    @Query(value = "INSERT INTO exhibition(content, imageId, userId, createDate) "
    			  + "VALUES(:content, :imageId, :userId, now())", nativeQuery = true)
	void saveExhibition(ExhibitionDTO ex);

	

	

}
