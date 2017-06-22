package org.lzdn.platform.service;

import java.util.List;

import org.lzdn.common.utils.Page;
import org.lzdn.platform.entity.User;
import org.lzdn.platform.entity.UserExample;

public interface UserService {

	int insert(User user);

	List<User> selectByExample(UserExample example);

	int deleteByPrimaryKey(Integer userId);

	int countByExample(UserExample example);
	
	List<User> selectBySelective(Page<User> page);

}
