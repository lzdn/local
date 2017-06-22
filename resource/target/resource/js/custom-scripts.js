(function($) {
	"use strict";
	var mainApp = {

		initFunction: function() {
			/*MENU 
			------------------------------------*/
			$('#main-menu').metisMenu();

			$(window).bind("load resize", function() {
				if($(this).width() < 768) {
					$('div.sidebar-collapse').addClass('collapse')
				} else {
					$('div.sidebar-collapse').removeClass('collapse')
				}
			});

		},

		initialization: function() {
			mainApp.initFunction();

		}

	}
	// Initializing ///

	$(document).ready(function() {
		mainApp.initFunction();
		LZDN_APP.prototype.currUser("#currUser");
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
			LZDN_APP.prototype.logout();
		});
		$("#userProfile").click(function() {
			var userinfo = LZDN_APP.prototype.checkUserStatus();
			if(!userinfo) {
				window.location.href = 'login.html';
			} else {
				window.location.href = 'home.html';
			}
		});
	});

}(jQuery));