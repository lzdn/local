package org.lzdn.platform.service.spring;

import java.util.List;

import javax.annotation.Resource;

import org.lzdn.common.utils.Page;
import org.lzdn.platform.dao.UserMapper;
import org.lzdn.platform.dto.UserDto;
import org.lzdn.platform.entity.User;
import org.lzdn.platform.entity.UserExample;
import org.lzdn.platform.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public int insert(UserDto record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		// TODO Auto-generated method stub
		return userMapper.selectByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int countByExample(UserExample example) {
		// TODO Auto-generated method stub
		return userMapper.countByExample(example);
	}

	@Override
	public List<User> selectBySelective(Page<UserDto> page) {
		// TODO Auto-generated method stub
		return userMapper.selectBySelective(page);
	}

 
}
