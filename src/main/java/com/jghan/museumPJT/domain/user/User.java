package com.jghan.museumPJT.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor  //Bean생성자
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가 전략이 db를 따라감
    @Column(name = "user_id")
    private int id;

    @Column(length = 100, unique = true) //Oauth2로그인을 위해 컬럼 늘리기
    private String username; //아이디

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String role;

    private LocalDateTime createDate;

    @PrePersist //DB에 INSERT되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    private String lastLogin;

    private int loginCount;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", createDate=" + createDate +
                ", lastLogin='" + lastLogin + '\'' +
                ", loginCount='" + loginCount + '\'' +
                '}';
    }
}
