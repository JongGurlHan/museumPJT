package com.jghan.museumPJT.service;

import com.jghan.museumPJT.dto.ExhibitionDTO;
import com.jghan.museumPJT.dto.SearchDTO;
import com.jghan.museumPJT.mapper.ExhibitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements  ExhibitionService{

    private final ExhibitionMapper exhibitionMapper;
    
    //전시검색
    @Override
	public List<ExhibitionDTO> searchExhibition(String keyword) {
    	return exhibitionMapper.selectExhibition(keyword);
	}

    //전체 전시조회
    @Override
    public List<ExhibitionDTO> getExhibitionAll() {
       return exhibitionMapper.selectExhibitionALL();
    }
    
    @Override
	public List<ExhibitionDTO> getExhibitionAll(SearchDTO searchDTO) {
    	return exhibitionMapper.selectExhibitionALLApi(searchDTO);
	}

    //전시조회 - eName으로
    @Override
    public String findExhibitionByName(String eName) {
        return exhibitionMapper.selectExhibitionByName(eName);
    }

    //전시저장
    @Override
    public boolean saveExhibition(ExhibitionDTO exhibitionDTO) {

        int queryResult = 0;
        queryResult = exhibitionMapper.insertExhibition(exhibitionDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<ExhibitionDTO> selectFinishedExhibition(String today) {
        return exhibitionMapper.selectFinishedExhibition(today);
    }

    @Override
    public void updateEdisplayZero(int eIdx) {
        exhibitionMapper.updateEdisplayZero(eIdx);
    }

	

	
}
