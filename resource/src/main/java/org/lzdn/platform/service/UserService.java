package org.lzdn.platform.service;

import java.util.List;

import org.lzdn.common.utils.Page;
import org.lzdn.platform.dto.UserDto;
import org.lzdn.platform.entity.User;
import org.lzdn.platform.entity.UserExample;

public interface UserService {

	int insert(UserDto record);

	List<User> selectByExample(UserExample example);

	int deleteByPrimaryKey(Integer userId);

	int countByExample(UserExample example);
	
	List<User> selectBySelective(Page<UserDto> page);

}
