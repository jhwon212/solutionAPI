package com.solution.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.solution.dto.User;
import com.solution.dto.UserAuthority;

public interface UserMapper {
	@Select("select username, password from user")
	public List<User> getUserList();
	
	@Select("select username, password from user where username = #{username}")
	public List<User> getUserListByUsername(@Param("username") String username);
	
	@Select("select username, authority from user_authority where username = #{username}")
	public List<UserAuthority> getUserAuthorityListByUsername(@Param("username") String username);
}
