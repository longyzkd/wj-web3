/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package me.web.upload;

import me.entity.User;
import me.repository.common.Page;
import me.service.upload.UploadService;
import me.utils.ExtUtils;
import me.web.CommonController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /task/
 * Create page : GET /task/create
 * Create action : POST /task/create
 * Update page : GET /task/update/{id}
 * Update action : POST /task/update
 * Delete action : GET /task/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController extends CommonController{

	private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private UploadService service;

	
	
	@RequestMapping(value="list" ,method = RequestMethod.GET)
	public @ResponseBody  Object list(Page<User> page,User user) {

//		page = service.getUsers(page,user);
		
		
		return ExtUtils.listToMap(page);
	}
	
	
}
