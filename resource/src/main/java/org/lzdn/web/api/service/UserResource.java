package org.lzdn.web.api.service;


public interface UserResource {

	public String addUser(String requestContext);

	public String login(String requestContext);

	public String queryUserList(Integer start,Integer limit,String userName);

/*
	public String queryUserByUserName(String userName);*/

	public String deleteUserByUserId(Integer userId);

}
