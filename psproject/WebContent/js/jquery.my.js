/**
 *
 */

/* jQuery plugin 제작 */
$.fn.msgShow = function(msg, obj) {
	var target = $(this).text(msg).css({"visibility": "visible","color" : "red"}).data("target");
	$("#"+target).css({"box-shadow": "0 0 2px 1px red", "border-width": "0", "padding" : "2px 12px"});
	if (obj) {
		$("#"+target).css({"box-shadow": "none", "border-width": 2, "padding" : "0 10px"});
	}
	
	return this;
	
}

$.fn.msgHide = function() {
	$(this).each(function(i, it) {
		var target = $(it).text("").css("visibility", "hidden").data("target");
		$("#"+target).css({"box-shadow": "none", "border-width": 2, "padding" : "0 10px"});
		
	});
	
	return this;
}