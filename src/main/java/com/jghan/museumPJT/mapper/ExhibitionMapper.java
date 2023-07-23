package com.jghan.museumPJT.mapper;
import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.dto.SearchDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExhibitionMapper {

    //전시 검색
    List<ExhibitionDTO> selectExhibition(String keyword);

    //전체 전시 조회
    List<ExhibitionDTO> selectExhibitionALL();

    List<ExhibitionDTO> selectExhibitionALLApi(SearchDTO searchDTO);

    List<ExhibitionDTO> selectExhibitionALLApi(SearchDTO searchDTO, int principalId);

    //전시조회
    String selectExhibitionByName(String eName);

    //전시등록
    int insertExhibition(ExhibitionDTO exhibitionDTO);

    //지난 전시조회
     List<ExhibitionDTO> selectFinishedExhibition(String today);

     //전시 진행여부 변경
     void updateEdisplayZero (int eIdx);


}

