package com.solution.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solution.dto.User;
import com.solution.dto.UserAuthority;
import com.solution.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> userList 					= this.userMapper.getUserListByUsername(username);
		List<UserAuthority> userAuthorityList	= this.userMapper.getUserAuthorityListByUsername(username);
		
		if(userList == null || userList.size() == 0){
			return null;
		}
		
		return new org.springframework.security.core.userdetails.User(userList.get(0).getUsername(), userList.get(0).getPassword(), getAuthorities(userAuthorityList));
	}
	
	private Set<GrantedAuthority> getAuthorities(List<UserAuthority> userAuthorityList){
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(UserAuthority userAuthority : userAuthorityList) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userAuthority.getAuthority());
			authorities.add(grantedAuthority);
		}
		return authorities;
}

}
