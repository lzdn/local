var app = {
	STATIC_URL: "http://localhost:8080/resource",
	STATIC_SEESION_ID: "LZDN_SESSION",
	STATIC_CURRUSER: "#currUser", //当前用户
	STATIC_MESSAGE: "#message", //提示信息
	initApp: function() {
		$('#main-menu').metisMenu(); /*MENU*/
		$(window).bind("load resize", function() {
			if($(this).width() < 768) {
				$('div.sidebar-collapse').addClass('collapse')
			} else {
				$('div.sidebar-collapse').removeClass('collapse')
			}
		});
	},
	initSession: function(data) {
		window.sessionStorage.setItem(app.STATIC_SEESION_ID, data);
	},
	checkUserStatus: function() { //检测用户是否已经登录
		var userSession = window.sessionStorage.getItem(app.STATIC_SEESION_ID);
		if(userSession == null || userSession == undefined) {
			$(app.STATIC_CURRUSER).html('未登录');
			$(app.STATIC_CURRUSER).click(function() {
				window.location.href = "login.html";
			});
			return false;
		} else {
			$(app.STATIC_CURRUSER).html($.parseJSON(userSession)[0].userName);
			return true;
		}
	},
	logout: function() {
		window.sessionStorage.removeItem(app.STATIC_SEESION_ID);
	},
	openUserProfile: function() {
		if(app.checkUserStatus()) {
			window.location.href = 'home.html';
		} else {
			window.location.href = "login.html";
		}
	}
}

var pager = {
	pageNo: 1,
	limit: 5,
	paginationsize: 5,
	actionUrl: '',
	condition: '',
	messageId: '',
	tabeId: '',
	pagination: function(data) {
		var dataJson = $.parseJSON(data);
		var ul = $('#pagination');
		if(ul) ul.empty();
		var page = dataJson.body.page;
		var totalPage = page.totalPage; //总页数
		if(totalPage > 0) {
			var pageNo = page.pageNo; //当前选中的页
			var Prev = (pageNo - 1 == 0) ? 1 : (pageNo - 1);
			var Next = (pageNo + 1) > totalPage ? totalPage : (pageNo + 1);
			ul.append("<li><a href=\"javascript:void(0);\" onclick=\"goPage(" + Prev + ")\">上一页</a></li>");
			for(var i = (Math.ceil(pageNo / pager.paginationsize) * pager.paginationsize - pager.paginationsize + 1); i <= (Math.ceil(pageNo / pager.paginationsize) * pager.paginationsize); i++) {
				if(i > totalPage) continue;
				if(pageNo == i)
					ul.append("<li class=\"active\"><a href=\"javascript:void(0);\" onclick=\"goPage(" + (i) + ")\">" + (i) + "</a></li>");
				else
					ul.append("<li><a href=\"javascript:void(0);\" onclick=\"goPage(" + (i) + ")\">" + (i) + "</a></li>");

			}
			ul.append("<li><a href=\"javascript:void(0);\" onclick=\"goPage(" + Next + ")\">下一页</a></li>");

		}

	},
	goPage: function(invoke) {
		var query = $.ajax({
			url: app.STATIC_URL + pager.actionUrl + "?pageNo=" + pager.pageNo + "&limit=" + pager.limit + "&condition=" + pager.condition,
			contentType: "text/html;charset=utf-8", //客户端请求数据类型
			data: '',
			type: 'get',
			cache: false,
			timeout: 5000,
			dataType: 'text', //服务器返回的数据
			//在请求之前调用的函数
			beforeSend: function() {
				$(pager.messageId).html('正在查询中...');
			},
			success: function(data) {
				$(pager.messageId).html('查询成功！');
				eval(invoke(data));
			},
			//调用执行后调用的函数
			complete: function(XMLHttpRequest, status) {　
				if(status == 'timeout') {
					//超时,status还有success,error等值的情况
					query.abort();　　　　　
					$(pager.messageId).html('请求超时，请检测网络！');　　　
				}
			},
			error: function() {
				$(pager.messageId).html('服务器异常！');
			}
		});
	}

}