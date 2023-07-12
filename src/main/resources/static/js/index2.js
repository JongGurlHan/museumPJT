
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
		//let divId = (model.estart+model.eend).replace(/-/g, "");
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
						<a href="" class="btn btn-outline-danger btn-sm"><i class="far fa-heart"></i></a>
		
					</div>
				</div>
			 </div>
		     `;
		    return item;
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
   
   
   

