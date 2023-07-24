package com.jghan.museumPJT.service;

import com.jghan.museumPJT.domain.likes.Likes;
import com.jghan.museumPJT.domain.likes.LikesRepository;
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

    private final LikesRepository likesRepository;
    
    //전시검색
    @Override
    @Transactional(readOnly = true)
    public List<ExhibitionDTO> searchExhibition(String keyword) {
    	return exhibitionMapper.selectExhibition(keyword);
	}

    //전체 전시조회
    @Override
    @Transactional(readOnly = true)
    public List<ExhibitionDTO> getExhibitionAll() {
       return exhibitionMapper.selectExhibitionALL();
    }

    @Override
    public List<ExhibitionDTO> getExhibitionAll(SearchDTO searchDTO) {
        return exhibitionMapper.selectExhibitionALLApi(searchDTO);
    }

    @Override
    @Transactional(readOnly = true)

    public List<ExhibitionDTO> getExhibitionAll(SearchDTO searchDTO, int principalId) {

        List<Likes> userLikeEx = likesRepository.findUserLikeEx(principalId);

        System.out.println("userLikeEx = " + userLikeEx);
     //   System.out.println("userLikeEx.toString() = " + userLikeEx.toString());



        //- 전시를 다 가져와야하긴하다
       //- 근데 사람마다 like한 전시가 제각각
       //



        List<ExhibitionDTO> exhibitions = exhibitionMapper.selectExhibitionALLApi(searchDTO, principalId);

//        exhibitions.forEach((ex)->{
//
//            ex.getLikes().forEach((like) -> {
//                if(like.getUser().getId() == principalId){ //해당 이미지에 좋아요한 사람들을 찾아서 현재 로긴한 사람이 좋아요 한것인지 비교
//                    ex.setLikeState(true);
//                }
//
//            });
//        });


        return exhibitions;
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
