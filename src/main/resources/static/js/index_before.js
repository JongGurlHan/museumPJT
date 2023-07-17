function searchExhibition() {
    /* event.preventDefault(); */

    let keyword = document.getElementById("search").value;

	console.log(keyword);

    $.ajax({
        type:"GET",
		url : `/api/search`,
        data: { keyword: keyword},
        //contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res =>{ //Http상태코드 200번대 done이 뜬다.
        res.forEach((ex) =>{
         console.log(ex);
         
         const exList = document.getElementById("exList"); // get the parent div element
         const childDivs = exList.querySelectorAll("div"); // get all child div elements
         childDivs.forEach((div) => div.remove()); // remove each child div element         
         
         let exItem = getExItem(ex);
         $("#exList").append(exItem);

        })
    }).fail(error => {
        console.log("오류", error);
    });
}


function getExItem(ex) {
    let item =
        `
                   <div class="col-xl-3 col-lg-4 col-md-6">
                        <div class="gallery-item h-100">
                            <img src="${ex.eimg}" class="img-fluid" alt="">
                            <div class="gallery-links" style="display: flex; justify-content: center; align-items: center; flex-direction: column; text-align: center;">
                                <p th:text="${ex.ename}">${ex.ename}</p>
                                <p th:text="${ex.estart} - ${ex.eend}">${ex.estart} - ${ex.eend}</p>
                                <p th:text="${ex.emuseum}">${ex.emuseum}</p>
                                <a href="${ex.elink}" class="details-link">
                                    <i class="bi bi-link-45deg"><h6>사이트이동</h6></i>
                                </a>
                            </div>
                        </div>
                    </div>
    `;
    return item;
}

    // 게시글 로드하기
	let pageNum= 1;
	
	function exLoad() {
		console.log("pageNum:", pageNum)
			$.ajax({
		        url:`/api/exhibition?pageNum=${pageNum}&pageSize=12`,
		        dataType : "json",
			     async: false
		    }).done(res=>{
		        console.log(res);
		        res.data.list.forEach((model) => {
					console.log(model);
		            let exModel = makeExItem(model)
		            $("#exList").append(exModel);
		        })
		    }).fail(error => {
		        console.log("오류", error);
		    });
	}
	
	function exArrange(option){
		  let divs = document.querySelectorAll(".col-xl-3.col-lg-4.col-md-6");
		  const sortedDivs = Array.from(divs).sort((a, b) => {
			    console.log(option);
			  	if(option == "old"){
			    	let aId = parseInt(a.getAttribute('id').substring(8)); // get the id of div a and convert it to an integer
			    	let bId = parseInt(b.getAttribute('id').substring(8)); // get the id of div b and convert it to an integer
			    	$(".new").addClass("active");
		            $(".old").removeClass("active");
			    	return aId - bId; // sort the divs in ascending order based on their id numbers			    	
			    }else if(option == "new"){
			    	let aId = parseInt(a.getAttribute('id').substring(0, 8)); // get the id of div a and convert it to an integer
			    	let bId = parseInt(b.getAttribute('id').substring(0, 8)); // get the id of div b and convert it to an integer
			    	$(".new").removeClass("active");
		            $(".old").addClass("active");

			    	return bId- aId 
			    }			    
			  });
			  
			  // append the sorted divs to their parent element in the new order
			  sortedDivs.forEach((div) => {
			    const parent = div.parentNode;
			    parent.removeChild(div);
			    parent.appendChild(div);
			  });
	}
	


	//전시 아이템 생성
	function makeExItem(model) {
		//let divId = (model.estart+model.eend).replace(/-/g, "");
		let item =
		    `
		    <div class="col-xl-3 col-lg-4 col-md-6" >
                        <div class="gallery-item h-100">
                            <img src="${model.eimg}" class="img-fluid" alt="">
                            <div class="gallery-links" style="display: flex; justify-content: center; align-items: center; flex-direction: column; text-align: center;">
                                <p th:text="${model.ename}">${model.ename}</p>
                                <p th:text="${model.estart} - ${model.eend}">${model.estart} - ${model.eend}</p>
                                <p th:text="${model.emuseum}">${model.emuseum}</p>
                                <a href="${model.elink}" class="details-link">
                                    <i class="bi bi-link-45deg"><h6>사이트이동</h6></i>
                                </a>
                                
                                <div style="width: 40px;font-size: 25px;border: 0;cursor: pointer;">
                                	<button>`;
									if(model.likeState){
										item += `<i class="fas fa-heart active" id="exLikeIcon-${model.eidx}" onclick="toggleLike(${model.eidx})"></i>`;

									}else{
								           item += `<i class="far fa-heart" id="exLikeIcon-${model.eidx}" onclick="toggleLike(${model.eidx})"></i>`;

							         }
								    item += `
								    <button>
								</div>
                                
                                
                            </div>
                        </div>
            </div>
		     `;
		    return item;
	}
	
	
	/*<div style="background-color: transparent; width: 40px;font-size: 25px;border: 0;cursor: pointer;">

    <button>`;
         if(image.likeState){
           item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;

         }else{
           item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;

         }

    item += `
    </button>
</div>*/

	exLoad();

	 $( document ).ready( function() {
			//스크롤 이벤트 최초 발생
		    $(window).scroll( ()=> {
		        let checkNum = $(window).scrollTop() - ( $(document).height() - $(window).height() );
		        if(checkNum < 1 && checkNum > -1){
		            pageNum++;
		            exLoad();
		        }
		    });
		  } );


   (function(w, d, a){
		w.__beusablerumclient__ = {
			load : function(src){
				var b = d.createElement("script");
				b.src = src; b.async=true; b.type = "text/javascript";
				d.getElementsByTagName("head")[0].appendChild(b);
			}
		};w.__beusablerumclient__.load(a);
	})(window, document, '//rum.beusable.net/script/b161207e112311u833/2dbeafc7a5');
   
   
   
   function showSelectedValue(selectedOption) {
	    const dropdownButton = selectedOption.closest('.dropdown').querySelector('.dropdown-toggle');
	    dropdownButton.innerHTML = selectedOption.innerHTML;
	  }
   
   
   
   function toggleLike(modelId) {
		let likeIcon = $(`#storyLikeIcon-${modelIdx}`);

		if (likeIcon.hasClass("far")) { //빈하트-> LIKE하겠다
		    $.ajax({
	        			type: "post",
	        			url: `/api/ex/${exId}/likes`,
	        			dataType: "json"
	        		}).done(res=>{

//	        			let likeCountStr = $(`#storyLikeCount-${imageId}`).text(); //b태그 내용의 text부분을 가져온다
//	        			let likeCount = Number(likeCountStr) + 1;
//	        			$(`#storyLikeCount-${imageId}`).text(likeCount);

	        			likeIcon.addClass("fas");
	        			likeIcon.addClass("active");
	        			likeIcon.removeClass("far");
	        		}).fail(error=>{
	        			console.log("오류", error);
	        		});

		} else {  //빨간하트 ->UNLIKE 하겠다.
		     $.ajax({
	         			type: "delete",
	         			url: `/api/image/${imageId}/likes`,
	         			dataType: "json"
	         		}).done(res=>{

	         			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
	         			let likeCount = Number(likeCountStr) - 1;
	         			$(`#storyLikeCount-${imageId}`).text(likeCount);

	         			likeIcon.removeClass("fas");
	         			likeIcon.removeClass("active");
	         			likeIcon.addClass("far");
	         		}).fail(error=>{
	         			console.log("오류", error);
	         		});
		}
	}
