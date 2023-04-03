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
