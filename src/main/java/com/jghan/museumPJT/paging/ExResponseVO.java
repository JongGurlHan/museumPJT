package com.jghan.museumPJT.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExResponseVO {
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
}
