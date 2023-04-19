package com.jghan.museumPJT.domain.exhibition;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

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
public class Exhibition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가 전략이 db를 따라감
    private int e_idx;

    @Column(nullable = false, length = 100, unique = true) 
    private String e_name;

    @Column(nullable = false)
    private String e_link;
    
    @Column(nullable = false)
    private String e_img;
    
    @Column(length = 50)
    private String e_start;

    @Column(length = 50)
    private String e_end;
    
    @Column(nullable = false, length = 50)
    private String e_museum;

    @ColumnDefault("1")
    @Column(nullable = false)
    private int    e_display;
 
    @Column(nullable = false, length = 100)
    private String e_address;

    @Column(nullable = false, length = 50)
    private String e_lat; //위도

    @Column(nullable = false, length = 50)
    private String e_long; //경도
    
    @Transient //DB에 컬럼이 만들어지지 않는다.
    private boolean likeState;
    
    

}
