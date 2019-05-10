/**
 * 
 *	서재진 
 * 
 */
$(function() {
	$(".t_buttons button").eq(1).click(function() {
		if(!$("input:radio").eq(0).prop("checked")) {
			event.preventDefault();
			alert("약관에 동의해 주세요.")
		}
	});
})