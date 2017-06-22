package org.lzdn.web.api.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.lzdn.common.utils.Page;
import org.lzdn.platform.dto.UserDto;
import org.lzdn.platform.entity.User;
import org.lzdn.platform.entity.UserExample;
import org.lzdn.platform.service.UserService;
import org.lzdn.web.api.service.UserResource;
import org.lzdn.web.base.BaseResource;
import org.lzdn.web.dto.Body;
import org.lzdn.web.dto.Header;
import org.lzdn.web.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

/*jersey 1.9 */
@Component
@Path("/user")
public class UserResourceImpl extends BaseResource implements UserResource {

	@Autowired
	private UserService userService;

	@POST
	@Produces({MediaType.APPLICATION_JSON}) // 响应数据类型
	@Consumes({MediaType.APPLICATION_JSON}) // 请求数据类型
	public String addUser(String requestContext) {
		UserDto record = new UserDto();
		record = (UserDto) simpleJsonToDto(requestContext, record);
		try {
			if (StringUtils.isNotEmpty(record.getUserName()) && StringUtils.isNotEmpty(record.getPassWord())) {
				userService.insert(record);
				return success;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fail;
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON) //
	@Consumes(MediaType.APPLICATION_JSON) //
	public String login(String requestContext) {
		UserDto record = new UserDto();
		record = (UserDto) simpleJsonToDto(requestContext, record);
		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo(record.getUserName());
		example.createCriteria().andPassWordEqualTo(record.getPassWord());
		List<User> list = userService.selectByExample(example);
		JSONArray array = new JSONArray();
		if (list != null && list.size() > 0) {
			array.add(list.get(0));
			return array.toString();
		} else {
			return fail;
		}
	}
/*
	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON) //
	@Override
	public String queryUserByUserName(@PathParam("userName") final String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		Page<UserDto> page = new Page<UserDto>();
		UserDto userDto = new UserDto();
		userDto.setUserName(userName);
		params.put("user", userDto);
		page.setParams(params);
		userService.selectBySelective(page);
		Result result = new Result(new Header(),new Body("page", page));
		// BeanUtils.copyProperties(source, target);
		return result.toString();
	
	}*/

	@GET
	@Produces(MediaType.APPLICATION_JSON) //
	@Override
	public String queryUserList(@QueryParam("pageNo") Integer pageNo, @QueryParam("limit") Integer limit, @QueryParam("condition") String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		Page<UserDto> page = new Page<UserDto>(pageNo==null?1:pageNo,limit==null?5:limit);
		UserDto userDto = new UserDto();
		if(StringUtils.isNotEmpty(userName))
		userDto.setUserName(userName+"%");
		params.put("user", userDto);
		page.setParams(params);
		userService.selectBySelective(page);
		Result result = new Result(new Header(),new Body("page", page));
		// BeanUtils.copyProperties(source, target);
		return result.toString();
	}

	@DELETE
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON) //
	@Override
	public String deleteUserByUserId(@PathParam("userId") final Integer userId) {
		try {
			if (userId != null) {
				userService.deleteByPrimaryKey(userId);
				return success;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fail;
	}
}
