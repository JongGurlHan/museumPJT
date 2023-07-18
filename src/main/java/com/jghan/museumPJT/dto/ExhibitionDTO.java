package com.jghan.museumPJT.dto;

import com.jghan.museumPJT.domain.likes.Likes;
import lombok.Data;

import java.util.List;

@Data
public class ExhibitionDTO {
    private int eIdx;
    private String eMuseum;
    private String eName;
    private String eLink;
    private String eImg;
    private String eStart;
    private String eEnd;
    private int eDisplay;
    private String eAddress;
    private String eLat; //위도
    private String eLong; //경도

    private boolean likeState;

    private List<Likes> likes;



}
