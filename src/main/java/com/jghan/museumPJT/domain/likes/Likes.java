package com.jghan.museumPJT.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jghan.museumPJT.domain.exhibition.Exhibition;
import com.jghan.museumPJT.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name ="likes_uk",
						columnNames = {"exhibitionId", "userId"}
				)
			}
		)
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @JoinColumn(name="exhibitionId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Exhibition exhibition;

    @JoinColumn(name="userId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
    
    private LocalDateTime createDate;

    @PrePersist //DB에 INSERT되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

	
	
}
