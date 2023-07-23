
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



	//전시 아이템 생성
	function makeExItem(model) {

		let item =
		    `
		    <div class="col-xl-3 col-lg-4 col-md-6" >

<!--		     <div class="col-lg-4 mb-4">-->
				<!--<div class="card">-->
                <div class="gallery-item h-100">

				  <img src="${model.eimg}" alt="" class="card-img-top">
					<div class="card-body">		
						
						<h5><p th:text="${model.ename}" style="margin-bottom: -0.25rem">${model.ename}</p></h5>

						<div class="rating">
						  <span><i class="fas fa-star" style="color: #e0e0e0;"></i></span>
						  <span class="text">9.94</span>
						</div>
			
						<br>
						<div>
						  <p style="margin-bottom: 0px;" th:text="${model.emuseum}">국립중앙박물관</p>
						  <p style="margin-bottom: 0px;" th:text="${model.estart} - ${model.eend}">2023-03-23 ~ 2023-09-23</p>
						</div>						 

						<br>
						
			
						<a href="${model.elink}" class="btn btn-outline-success btn-sm">사이트 이동</a>

						<div>`;
							if(model.likeState){
								item += `<i class="fas fa-heart active" style="color: #ff0000;" id="storyLikeIcon-${model.eidx}" onclick="toggleLike(${model.eidx}, username)"></i>`;
							}else{
								item +=`<i class="far fa-heart" style="color: #ff0000;" id="storyLikeIcon-${model.eidx}" onclick="toggleLike(${model.eidx}, username)"></i>`;
							}
                        item += `
						</div>	
						
						               		
		
		
					</div>
				</div>
			 </div>
		     `;
		    return item;
	}


	//좋아요, 안좋아요
	function toggleLike(exId, username) {

		console.log(exId);
		console.log(typeof exId);

		if (username == null) {
			alert("로그인 해주세요!");
			return false;
		}

		let likeIcon = $(`#storyLikeIcon-${exId}`);

		if (likeIcon.hasClass("far")) { //빈하트-> LIKE하겠다

			$.ajax({
				type: "post",
				url: `/api/ex/${exId}/likes`,
				dataType: "json"
			}).done(res => {
				likeIcon.addClass("fas");
				likeIcon.addClass("active");
				likeIcon.removeClass("far");
			}).fail(error => {
				console.log("오류", error);
			});

		} else {  //빨간하트 ->UNLIKE 하겠다.

			$.ajax({
				type: "delete",
				url: `/api/ex/${exId}/likes`,
				dataType: "json"
			}).done(res => {
				likeIcon.removeClass("fas");
				likeIcon.removeClass("active");
				likeIcon.addClass("far");
			}).fail(error => {
				console.log("오류", error);
			});
		}
	}



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
   
   
   

