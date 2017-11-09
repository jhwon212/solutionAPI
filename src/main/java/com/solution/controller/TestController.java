package com.solution.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solution.dto.User;
import com.solution.mapper.UserMapper;

@RestController
public class TestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> test() throws Exception {
		
		List<User> userList = userMapper.getUserList();
		for(int i=0; i<userList.size(); i++){
			log.info(userList.get(i).getUsername() + ":" + userList.get(i).getPassword());
		}
		
		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);
		headers.setContentType(mediaType);
		
		return new ResponseEntity<List<User>>(userList, headers, HttpStatus.OK);
	}

}
