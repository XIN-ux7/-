package com.liddhome.admin.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liddhome.admin.dao.AdminDao;
import com.liddhome.admin.entity.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("adminname", adminname);
			map.put("adminpwd", adminpwd);
			return adminDao.findByAdminnameAndAdminpwd(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
