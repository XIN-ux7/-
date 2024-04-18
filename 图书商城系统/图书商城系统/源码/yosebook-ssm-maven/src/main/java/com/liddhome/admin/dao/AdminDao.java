package com.liddhome.admin.dao;

import java.sql.SQLException;
import java.util.Map;

import com.liddhome.admin.entity.Admin;

public interface AdminDao {
	
	public Admin findByAdminnameAndAdminpwd(Map<String,Object> map) throws SQLException;
}
