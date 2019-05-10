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
		$(".modal").show();
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
})