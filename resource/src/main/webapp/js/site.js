(function($) {
	"use strict";
	// Initializing ///
	$(document).ready(function() {
		app.initApp();
		app.checkUserStatus();
		$("#sideNav").click(function() {
			if($(this).hasClass('closed')) {
				$('.navbar-side').animate({
					left: '0px'
				});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({
					'margin-left': '260px'
				});

			} else {
				$(this).addClass('closed');
				$('.navbar-side').animate({
					left: '-260px'
				});
				$('#page-wrapper').animate({
					'margin-left': '0px'
				});
			}
		});
		$('#logout').click(function() {
			app.logout();
		});
		$("#userProfile").click(function() {
			app.openUserProfile();
		});
	});
}(jQuery));