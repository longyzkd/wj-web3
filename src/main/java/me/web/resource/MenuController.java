/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package me.web.resource;

import javax.servlet.ServletRequest;

import me.service.accout.ShiroDbRealm.ShiroUser;
import me.service.resource.ResourceService;
import me.utils.Constants;
import me.utils.ExtUtils;
import me.web.CommonController;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单
 * 
 * List page : GET /task/
 * Create page : GET /task/create
 * Create action : POST /task/create
 * Update page : GET /task/update/{id}
 * Update action : POST /task/update
 * Delete action : GET /task/delete/{id}
 * 
 * @author wj
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends CommonController {


	

	@Autowired
	private ResourceService service;

	@RequestMapping(value="list" ,method = RequestMethod.GET)
	public @ResponseBody  Object list( Model model,
			ServletRequest request) {

		String view = request.getParameter("view");
		if(Constants.TREE_VIEW.list.name().equals(view)){//面板
			return ExtUtils.listToMap(service.getPanels(getCurrentUserId()));
		}else if(Constants.TREE_VIEW.node.name().equals(view)){//树
			String id = request.getParameter("id");
			return ExtUtils.listToMap(service.getNodes(id));
		}
		
		return null;
	}
	
	
}

