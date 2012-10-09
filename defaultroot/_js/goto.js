$(function(){
	$('.btn').toggle(function(){
		
		$('#sideBar').animate({marginLeft: "-=150px"}, { queue: false, duration: 500 });
		parent.document.getElementById('x').cols='20,*'; 
	},function(){
		$('.fixedSideBar').css('display','none')
		$('#sideBar').animate({marginLeft: "+=150px"}, { queue: false, duration: 500 });
		parent.document.getElementById('x').cols='170,*'; 
	})
})