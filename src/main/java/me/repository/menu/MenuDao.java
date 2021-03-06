/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package me.repository.menu;

import java.util.List;

import me.entity.Menu;
import me.entity.Office;
import me.repository.common.CommonDao;
import me.repository.common.Page;
import me.repository.common.Parameter;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class MenuDao extends CommonDao<Menu>  {
	
	
	public List<Menu> findPanels(Long userId) {
		
		StringBuilder hql  =  new StringBuilder();
		
		hql.append(" select m from Menu m ,User u ,Role r , UserRole ur ,RoleMenu rm where u.id=:p1 and u.id = ur.userId and ur.roleId = r.id and r.id = rm.roleId and rm.menuId = m.id ");
//		hql.append(" and m.parentId is null" );
		hql.append(" order by m.sort");
		return find(hql.toString(), new Parameter(userId));
	}

	public List<Menu> findNodes(String id) {
		
		DetachedCriteria  detachedCriteria =  DetachedCriteria.forClass(Menu.class);
		detachedCriteria.add(Restrictions.eq("parentId", Long.valueOf(id)))
						.addOrder(Order.asc("sort"));
		return find(detachedCriteria);
	}


	


	
}
