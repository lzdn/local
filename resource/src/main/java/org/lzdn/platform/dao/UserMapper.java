package org.lzdn.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.lzdn.common.utils.Page;
import org.lzdn.platform.entity.User;
import org.lzdn.platform.entity.UserExample;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectBySelective(Page<User> page);
}