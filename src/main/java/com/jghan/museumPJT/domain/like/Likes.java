package com.jghan.museumPJT.domain.like;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    @ManyToOne //기본 패치전략은 EAGER
    private Exhibition exhibition;
    
    @JoinColumn(name="userId")
	@ManyToOne
	private User user;
    
    private LocalDateTime createDate;

    @PrePersist //DB에 INSERT되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

	
	
}
