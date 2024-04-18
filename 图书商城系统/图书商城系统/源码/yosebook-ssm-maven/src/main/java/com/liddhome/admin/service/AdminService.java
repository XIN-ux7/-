package com.liddhome.admin.service;

import com.liddhome.admin.entity.Admin;

public interface AdminService {	
	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd);	
}
