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
         let exItem = getExItem(ex);
         $("#exItemList").append(exItem);

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

	//전시 아이템 생성
	function makeExItem(model) {
		let item =
		    `
		    <div class="col-xl-3 col-lg-4 col-md-6">
                        <div class="gallery-item h-100">
                            <img src="${model.eimg}" class="img-fluid" alt="">
                            <div class="gallery-links" style="display: flex; justify-content: center; align-items: center; flex-direction: column; text-align: center;">
                                <p th:text="${model.ename}">${model.ename}</p>
                                <p th:text="${model.estart} - ${model.eend}">${model.estart} - ${model.eend}</p>
                                <p th:text="${model.emuseum}">${model.emuseum}</p>
                                <a href="${model.elink}" class="details-link">
                                    <i class="bi bi-link-45deg"><h6>사이트이동</h6></i>
                                </a>
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
