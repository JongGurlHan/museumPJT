package com.jghan.museumPJT.mapper;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExhibitionMapper {

    //전체 전시 조회
    List<ExhibitionDTO> selectExhibitionALL();

    //전시조회
    String selectExhibitionByName(String eName);

    //전시등록
    int insertExhibition(ExhibitionDTO exhibitionDTO);

    //지난 전시조회
     List<ExhibitionDTO> selectFinishedExhibition(String today);

     //전시 진행여부 변경
     void updateEdisplayZero (int eIdx);



}

