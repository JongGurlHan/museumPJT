package com.jghan.museumPJT.mapper;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExhibitionMapper {

    //전시조회
    String selectExhibitionByName(String eName);

    //전시등록
    int insertExhibition(ExhibitionDTO exhibitionDTO);

    //지난 전시조회
     List<ExhibitionDTO> selectFinishedExhibition(String today);

     void updateEdisplayZero (int eIdx);
}

