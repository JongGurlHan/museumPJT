package com.jghan.museumPJT.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    
    @Modifying
    @Query(value = "UPDATE user SET lastLogin = NOW() WHERE id = :id", nativeQuery = true)
    void updateLastLogin(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE user SET loginCount = user.loginCount+1 WHERE id = :id", nativeQuery = true)
	void updateLoginCount(@Param("id") int id);
    
    
}
