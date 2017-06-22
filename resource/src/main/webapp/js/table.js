var tab = {
	showUserTable: function(data) {
		var responseContext = $.parseJSON(data);
		var tbody = $('#userTable tbody');
		if(tbody) {
			tbody.empty(); //清空表格
			$.each(responseContext.body.page.results, function(idx, item) {
				var newNode = "<tr><td>" + (idx + 1) + "</td><td>" + item.id + "</td><td>" + item.userName + "</td><td>" + item.email + "</td><td><a href=\"javascript:void();\" onclick=\"deleteUserByUserId(" + item.id + ")\">删除</a>|<a href=\"javascript:void();\">修改</a></td></tr>";
				tbody.append(newNode);
			});
			pager.pagination(data);
		}

	},
	createUser: function() {
		var tbody = $('#userTable tbody');
		var newNode = "<tr><td>*</td><td>*</td><td><input type='text' name='userName' placeholder='用户名称'/>-<input type='password' name='password' placeholder='密码'/></td><td><input type='text' name='email' placeholder='邮箱'/></td><td><a href=\"javascript:void();\" onclick='submitForm()'>保存</a>|<a href=\"javascript:void();\" onclick='deleteNewUser(this)'>删除</a></td></tr>";
		tbody.append(newNode);
	}

}