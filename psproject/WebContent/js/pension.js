$(function() {
	$(".img_wrap").click(function() {
		var rmidx = $(this).data("rmidx");
		$.ajax("imgCnt",{
			data : "rmidx=" + rmidx,
			success : function(data) {
				//alert(rmidx+" : " + data.trim());
				
				var testStr = '<ul class="slider">';
				
				for (var i = 1; i <= data; i++) {
					var mainPath = web_path + "/" + oridx + "/" + rmidx + "/main/" + i + ".jpg";
					//console.log(mainPath);
					testStr += "<li><img src='"+ mainPath + "'></li>";
				}
				testStr += '</ul><ul class="sliderPager">';
				
				for (var j = 1; j <= data; j++) {
					var thumbPath = web_path + "/" + oridx + "/" + rmidx + "/thumb/" + j + ".jpg";
					testStr += "<li><a href='#' data-slide-index='"+ (j-1) +"'>";
					testStr += "<img src='"+ thumbPath + "'></a></li>";
				}
				testStr += "</ul>";
				//console.log(testStr);
				
				$("#ex1").html(testStr);
				$("#open").click();
			}
		});
	});
	
	$("#open").click(function() {
		$("#ex1").show();
		$(".slider").bxSlider({
			preloadImages: 'all',
			pagerCustom : '.sliderPager',
			mode:'fade'
		});
	
		$(".sliderPager").bxSlider({
			touchEnabled : false,
			pager : false,
			slideWidth : 180,
			slideHeight : 162,
			minSlides : 5,
			maxSlides : 5
		});
	})
	
	$(window).scroll(function() {
		if($(this).scrollTop() >= 456) {
			$(".cont_tab").addClass("cont_tab_scrolled");
		} else {
			$(".cont_tab").removeClass("cont_tab_scrolled");
		}
	});
	
	/* modal Ex2 안쪽의 별점 클릭 이벤트 */
	$(".input-star i").click(function(){
		var idx = $(this).index();
		
		$(".input-star i").each(function(i){
			if(idx >= i){
				$(this).removeClass("fa-star-o").addClass("fa-star");
			}
			else{
				$(this).removeClass("fa-star").addClass("fa-star-o");
			}
		})
		$("#score").val(idx+1);
		console.log($("#score").val());
	});
	
	
	// 별점 작성 버튼 이벤트
	$("#scoreRegBtn").click(function(){
		$(".modal-footer button").hide()
			.eq(0).show()
			.end().eq(3).show();
		$("#ex2").modal("show");
	});
	
	$(".modal-footer button").eq(0).click(function(e){
		e.preventDefault();
		//alert('클릭');
		var frm = $(document.scoreFrm).get(0);
		var formData = new FormData(frm);
		
		$.ajax({
			url : "/writeReview",
			data : formData,
			type : 'post',
			dataType : 'json',
			contentType : false,
			processData : false
		}).done(function(data) {
			if(data.result == 'success'){
				showList();
			}
			else if(data.result == "NOT-LOGIN"){
				alert("로그인 이후에 사용해주세요.")
			}
			$('.blocker').click();
		})
	});
})

function makeStarHtml(number){
	var span =$("<span>");
	for(var i = 0; i < 5; i++){
		span.append($("<i>").addClass("fa fa-lg fa-star").css("color",number >= i+1 ? "#fc0" : "#ccc"));
	}
	return span.html();
}

function showList(){
	$.ajax({
		url : 'reviewOverall?psidx=' + psidx,
		success : function(data){
			console.log(data);
			var str = "<h4>";
			var avg = data.avg;
			for(var i = 0; i < 5; i++){
				if(avg >= 1){
					s = 'fa-star'
				}
				else if(avg > 0){
					s = 'fa-star-half-o'
				}
				else{
					s = 'fa-star-o'
				}
				avg--;
				str += "<i class='fa fa-lg " + s + "'></i>";
			}
			str += "<br>" + (data.avg || '평가없음')  + "</h4>";
			$("#overall").html(str);
			
			if(data.avg){
				str = "";
				var arr = ["5","4","3","2","1"]
				for(var i = 0; i <5; i++){
					str += "<div class='clearfix'><div class='float-left'>"
					for(var j = 0; j < 5; j++){
						if(i + j <= 4){
							str += '<i class="fa fa-lg fa-star" style="color:#fc0"></i>';			
						}
						else{
							str += '<i class="fa fa-lg fa-star" style="color:#ccc"></i>';
						}
					}
					str += "</div><div class='progress float-right'>"
					str += "<div class='progress-bar' style ='width:"
					str += data[arr[i]] / data.cnt * 100  + "%'>" +(data[arr[i]] == 0? '' : data[arr[i]]);
					str += "</div></div></div>";
				}
				$("#overall").html(function(i,html){
					return html + str;
				});
			}
		}
	}).done(function(){
		$.ajax({
			url : 'listReview?psidx=' + psidx,
				beforeSend : function(){
					$(".review-detail").html("<div class='center'><img src = 'images/icon/progress-icon-gif-1.jpg'></div>")
				},
		
			success : function(data){
				console.log(data);
				var str = "<ul>";
				for(var i in data){
					str += "<li>";
					str += "<div class='reviewContentImgWrapper'><h5>" + data[i].title + "</h5>";
					str += "<p>" + data[i].cont + "</p>";
					str += "<p class='small'>" + data[i].email + " | " + data[i].regdate + " | " ;

					str += makeStarHtml(data[i].score);
					
					str += "</p></div>";
					str += "<div class='reviewDetailImgWrapper'>";
					
					if(data[i].imgList.length != 0){
						str += "<img src='upload/" + data[i].imgList[0].realName + "'"; 
						str += " alt='" +data[i].imgList[0].originName + "' ";
						str += "onerror = 'this.src = \"images/img-error.png\"'>"
					}
					
					str += "</div>";
					str += "</li>";
					}
				str += "</ul>"
				$('.review-detail').html(str);	
			}
		})
	})
}