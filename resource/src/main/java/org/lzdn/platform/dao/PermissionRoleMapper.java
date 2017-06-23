package org.lzdn.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.lzdn.platform.entity.PermissionRoleExample;
import org.lzdn.platform.entity.PermissionRoleKey;

public interface PermissionRoleMapper {
    int countByExample(PermissionRoleExample example);

    int deleteByExample(PermissionRoleExample example);

    int deleteByPrimaryKey(PermissionRoleKey key);

    int insert(PermissionRoleKey record);

    int insertSelective(PermissionRoleKey record);

    List<PermissionRoleKey> selectByExample(PermissionRoleExample example);

    int updateByExampleSelective(@Param("record") PermissionRoleKey record, @Param("example") PermissionRoleExample example);

    int updateByExample(@Param("record") PermissionRoleKey record, @Param("example") PermissionRoleExample example);
}