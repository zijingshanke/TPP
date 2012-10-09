
$(function(){
	$('.dataList tr:even').css({background: '#F8F8F8'})
	$('.dataList tr').hover(function(){
		$(this).css({background: '#CED5E5'})
	},function(){
		$(this).css({background: 'none'})
	})
	$('.dataList tr:even').hover(function(){
		$(this).css({background: '#CED5E5'})
	},function(){
		$(this).css({background: '#F8F8F8'})
	})
	$('.tfoot').hover(function(){
		$(this).css({background: '#FFF'})
	})
	
})